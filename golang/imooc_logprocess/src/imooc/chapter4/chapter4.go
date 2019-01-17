package chapter4

import (
	"bufio"
	"fmt"
	"io"
	"net/url"
	"os"
	"regexp"
	"strconv"
	"strings"
	"time"
)

//日志消息体
type Message struct {
	Ip        string    //客户端ip
	Time      time.Time //请求时间
	Method    string    //方法
	Path      string    //请求地址
	Scheme    string    //请求协议
	Status    int       //状态
	BytesSent int       //流量
	UserAgent string    //客户端
}

type LogProcess struct {
	Rc    chan []byte
	Wc    chan *Message
	Read  Reader
	Write Writer
}

//读取
type Reader interface {
	Read(rc chan []byte)
}

type ReadFromFile struct {
	Path string //文件路径
}

func (r *ReadFromFile) Read(rc chan []byte) {
	//打开文件
	f, err := os.Open(r.Path)
	if err != nil {
		panic(fmt.Sprintf("Open file error: %s\n", err.Error()))
	}
	//从文件末尾逐行读取文件内容
	f.Seek(0, 2) //0表示偏移量，2表示文件末尾(具体看源码注释)
	rd := bufio.NewReader(f)
	for {
		line, err := rd.ReadBytes('\n') //读取单行内容
		if err == io.EOF {
			//读取到文件末尾
			time.Sleep(500 * time.Microsecond)
			continue
		} else if err != nil {
			panic(fmt.Sprintf("ReadBytes error: %s\n", err.Error()))
		}
		rc <- line[:len(line)-1]
	}
}

//写入
type Writer interface {
	Write(wc chan *Message)
}

type WriteToInfluxDB struct {
}

func (w *WriteToInfluxDB) Write(wc chan *Message) {
	for v := range wc {
		fmt.Println(v)
	}
}

//处理模块

func (l *LogProcess) Process() {

	//192.168.2.163 - - [17/Jan/2019:15:56:03 +0800] "GET /v2/getAppUpdate HTTP/1.1" 200 2086 "-" "okhttp/3.8.0"
	reg := regexp.MustCompile(`([\d\.]+)\s+([^ \[]+)\s+([^ \[]+)\s+\[([^\]]+)\]\s+\"([^"]+)\"\s+(\d+)\s+(\d+)\s+\"([^"]+)\"\s+\"([^"]+)\"`)
	loc, _ := time.LoadLocation("Asia/Shanghai")

	for v := range l.Rc {
		str := string(v)
		matchResult := reg.FindStringSubmatch(str)

		//0为匹配的字符串自身
		if len(matchResult) != 10 {
			fmt.Printf("FindStringSubmatch fail: %s\n", str)
			continue
		}
		msg := &Message{}

		//192.168.2.163
		msg.Ip = matchResult[1]

		//17/Jan/2019:15:56:03 +0800
		t, err := time.ParseInLocation("02/Jan/2006:15:04:05 +0800", matchResult[4], loc)
		if err != nil {
			fmt.Printf("ParseInLocation fail: %s, %s\n", err.Error(), matchResult[4])
		}
		msg.Time = t

		//GET /v2/getAppUpdate HTTP/1.1
		reqSli := strings.Split(matchResult[5], " ")
		if len(reqSli) != 3 {
			fmt.Printf("strings.Split fail\n")
			continue
		}
		u, err := url.Parse(reqSli[1])
		if err != nil {
			fmt.Printf("url parse fail: %s\n", reqSli[1])
			continue
		}
		msg.Method = reqSli[0]
		msg.Path = u.Path
		msg.Scheme = reqSli[2]

		//200
		status, _ := strconv.Atoi(matchResult[6])
		msg.Status = status

		//2086
		byteSent, _ := strconv.Atoi(matchResult[7])
		msg.BytesSent = byteSent

		//okhttp/3.8.0
		msg.UserAgent = matchResult[9]

		l.Wc <- msg
	}
}
