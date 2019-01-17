package main

import (
	"time"
	"imooc/chapter3"
)

func main() {

	r := &chapter3.ReadFromFile{
		Path: "./access.log",
	}
	w := &chapter3.WriteToInfluxDB{
	}

	lp := &chapter3.LogProcess{
		Rc:    make(chan []byte),
		Wc:    make(chan string),
		Read:  r,
		Write: w,
	}

	go lp.Read.Read(lp.Rc)
	go lp.Process()
	go lp.Write.Write(lp.Wc)

	time.Sleep(10 * time.Second)
}