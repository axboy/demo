package chapter3

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strings"
	"time"
)

type LogProcess struct {
	Rc    chan []byte
	Wc    chan string
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
	//从文件末尾逐行读取文件内容，将指针移动到末尾
	//0表示偏移量，2表示文件末尾(具体看源码注释)
	f.Seek(0, 2)
	rd := bufio.NewReader(f)
	for {
		//读取文件内容，直到遇到换行符，即读取单行内容
		line, err := rd.ReadBytes('\n')
		if err == io.EOF {
			//读取到文件末尾
			time.Sleep(500 * time.Microsecond)
			continue
		} else if err != nil {
			panic(fmt.Sprintf("ReadBytes error: %s\n", err.Error()))
		}
		//去除最后一个字符
		rc <- line[:len(line)-1]
	}
}

//写入
type Writer interface {
	Write(wc chan string)
}

type WriteToInfluxDB struct {
}

func (w *WriteToInfluxDB) Write(wc chan string) {
	for v := range wc {
		fmt.Println(v)
	}
}

//处理模块
func (l *LogProcess) Process() {
	//处理模块
	for v := range l.Rc {
		l.Wc <- strings.ToUpper(string(v)) //byte转string
	}
}
