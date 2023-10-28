package it.polito.bigdata.hadoop.lab;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.conf.Configuration;

/**
 * Lab  - Mapper
 */

/* Set the proper data types for the (key,value) pairs */
class MapperBigDataBonuesTrack extends Mapper<
                    LongWritable, // Input key type
                    Text,         // Input value type
                    Text,         // Output key type
                    IntWritable> {// Output value type
    
    protected void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        String param = conf.get("contains");
        String[] words = value.toString().split("\\s+");
        if(words[0].contains(param) || words[1].contains(param)){
            context.write(new Text(words[0] + " " + words[1]), new IntWritable(Integer.parseInt(words[2])));
        }
    }
}
