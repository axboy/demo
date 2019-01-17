package chapter1

import (
	"fmt"
	"strings"
)

type LogProcess struct {
	Path        string
	InfluxDBDsn string
	Rc          chan string
	Wc          chan string
}

//读取模块
func (l *LogProcess) ReadFromFile() {
	line := "message" //假装读取到一条消息
	l.Rc <- line
}

//处理模块，将读取的消息转为大写
func (l *LogProcess) Process() {
	data := <-l.Rc
	l.Wc <- strings.ToUpper(data)
}

//写入模块
func (l *LogProcess) WriteToInfluxDB() {
	fmt.Println(<-l.Wc)
}
