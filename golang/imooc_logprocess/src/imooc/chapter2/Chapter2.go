package chapter2

import (
	"fmt"
	"strings"
)

type LogProcess struct {
	Rc    chan string
	Wc    chan string
	Read  Reader
	Write Writer
}

//读取
type Reader interface {
	Read(rc chan string)
}

type ReadFromFile struct {
	Path string
}

func (r *ReadFromFile) Read(rc chan string) {
	line := "message"
	rc <- line
}

//写入
type Writer interface {
	Write(wc chan string)
}

type WriteToInfluxDB struct {
}

func (w *WriteToInfluxDB) Write(wc chan string) {
	fmt.Println(<-wc)
}

//处理模块
func (l *LogProcess) Process() {
	data := <-l.Rc
	l.Wc <- strings.ToUpper(data)
}
