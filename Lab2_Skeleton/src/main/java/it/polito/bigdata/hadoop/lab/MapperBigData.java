package it.polito.bigdata.hadoop.lab;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.conf.Configuration;

class MapperBigData extends Mapper<LongWritable, Text, Text, IntWritable> {
    
    protected void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        String param = conf.get("startString");
        String[] words = value.toString().split("\\s+");
        if(words[0].startsWith(param)){
            context.write(new Text(words[0]), new IntWritable(Integer.parseInt(words[1])));
        } else {
            Counter customCounter = context.getCounter("Filter", "Discarded");
            customCounter.increment(1);
        }   

        Counter customCounter = context.getCounter("Filter", "Total");
        customCounter.increment(1);
    }
}
