package cn.axboy.demo.interview.test;
import java.io.UnsupportedEncodingException;
import java.lang.*;
import java.net.URLDecoder;
import java.util.*;
/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/29 11:46
 */
public class Main{
    public static class Student{
        private static int id = 0;
        String name;
        int score;
        int no;

        Student(String name, int score){
            this.name = name;
            this.score = score;
            this.no = id++;

        }

        int compare(Student other){
            if(this.score == other.score){
                return this.no > other.no ? 1 : -1;
            }
            if(this.score > other.score){
                return 1;
            }else{
                return -1;
            }
        }

        void print(){
            System.out.format("%s %d\n", name, score);
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //String str = "\\x17S\\xd8\\x93\\xa4\\xfd\\xc0K\\x9e~\\xab\\xcf\\xa5\\xd0\\x0f\\xc6\\xb5\\x13h\\xa4\\xed\\x17#\\xad\\xa9\\x0e\\xc9\\x82\\x85\\xd1";
        String str = "\\x22\\xE5\\x93\\x88\\xE5\\x93\\x88\\x22";
        String s1 = str.replaceAll("\\\\x", "%");
        String decode = URLDecoder.decode(s1, "utf-8");
        System.out.println(decode);


        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int order = input.nextInt();
        PriorityQueue<Student> heap = new PriorityQueue<>((a1, a2)->a1.compare(a2));
        input.nextLine();
        for(int i = 0; i < num; i++){
            String line = input.nextLine();
            String[] arr = line.split(" ");
            System.out.format("line:[%s], arr:[%d]\n",line, arr.length);
            //todo check arr length
            heap.offer(new Student(arr[0], Integer.valueOf(arr[1])));
            //heap.offer(new Student(arr[0], i));
        }
        System.out.println(">>>>");
        while(heap.size() > 0){
            heap.poll().print();
        }
    }
}
