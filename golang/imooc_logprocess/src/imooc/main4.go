package main

import (
	"time"
	"imooc/chapter4"
)

func main() {

	r := &chapter4.ReadFromFile{
		Path: "./access.log",
	}
	w := &chapter4.WriteToInfluxDB{
	}

	lp := &chapter4.LogProcess{
		Rc:    make(chan []byte),
		Wc:    make(chan *chapter4.Message),
		Read:  r,
		Write: w,
	}

	go lp.Read.Read(lp.Rc)
	go lp.Process()
	go lp.Write.Write(lp.Wc)

	time.Sleep(30 * time.Second)
}