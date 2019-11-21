package main.java.chapter05.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

public class Main {

	public static void main(String[] args) throws Exception {
	
		TopologyBuilder builder = new TopologyBuilder();																																																																																																																																																																																																																																																																																																																																																					    
		//设置spout id和spout实例对象
		builder.setSpout("word-reader", new ParallelFileSpout());
		//设置第一bolt
		builder.setBolt("word-normalizer", new DetectionBolt(), 1)
				.fieldsGrouping("word-reader", new Fields("word1"));
		//设置第二噶bolt
		builder.setBolt("word-counter", new CountBolt()).fieldsGrouping(
				"word-normalizer", new Fields("word"));
		Config conf = new Config();
		
		/*
		//运行Topology应用程序
		StormSubmitter.submitTopology("wordCounterTopology", conf,
				builder.createTopology());
		*/
		
		conf.setDebug(true);


		if (args != null && args.length > 0) {
			conf.setNumWorkers(3);
			StormSubmitter.submitTopologyWithProgressBar(args[0], conf,
					builder.createTopology());
		} else {

			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("test", conf, builder.createTopology());
			//Utils.sleep(10000);
			//cluster.killTopology("test");
			//cluster.shutdown();
		}
		
	}

}
