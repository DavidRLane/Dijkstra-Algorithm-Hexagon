Trump Twitter Generator
CS582 semester long project Spring 2017
Ahmad Elhamad, Ashwin Chichirhe, David Lane, Henry Curtis, Tiger Anne Wing-Lawrence

File Manifest: Main.java, Word_Synth.java, twitter-1.71.1-py2.py3-none-any.whl, twitter_streaming.py, tweetsjson.py, posttweets.py, Trump Generator Output.txt, fulltweets.txt, fulltweetsv2.txt, fulltweetsv3.txt, fulltweets15-17.txt

	NOTE: fulltweets.txt (least filtered, 22,136 tweets), fulltweetsv2.txt (attempted to filter out all replys, 19,610 tweets), fulltweetsv3.txt (filtered all "." in case they throw off the synthesizer, also 19,610 tweets), fulltweets15-17.txt (all previous formatting, only from 2015-2017, 7,072 tweets), Trump Generator Output.txt (file generated after running through synthesizer)

Operation instructions:
	NOTE: this code uses our login tokens we generate from a new twitter account. To use this code for other purposes, one should use their own generated tokens that can be easily obtained through following twitter's instructions
	NOTE2: the twitter API requires the twitter library provided to be installed. Install python if not installed already. Call "pip twitter-1.71.1-py2.py3-none-any.whl" on command line. If pip is not recognized, then you have an older version of Python. To work around this obtain the official "get-pip.py" file from
		Python's website, run it, and now the aforementioned call will work. Using API (only uses most recent 4000 tweets): on command line, run "twitter_streaming.py > twitteroutput.txt", put new text file into the same directory as the java files, run Word_synth.java first then main.java and it will create 10 synthesized tweets that print to the screen as well as Trump Generator Output.txt file
	Using all tweets: download all condensed_20XX.json files from "https://github.com/bpb27/trump_tweet_data_archive", open tweetsjson.py and' change the path to where the files were downloaded, run "tweetsjson.py > twitteroutput.txt",  put new text file into the same directory as the java files, run Word_synth.java first then main.java and it will create 10 synthesized tweets that print to the screen as well as Trump Generator Output.txt file
	Using API with other users: Open twitter_streaming.py, change "user = "XXXXXX" to the username you desire, run "twitter_streaming.py > twitteroutput.txt", put new text file into the same directory as the java files, run Word_synth.java first then main.java and it will create 10 synthesized tweets that print to the screen as well as Trump Generator Output.txt file
	In order to generate the Hash Tables and the sentences, the Java files need to be placed in your working directory along with the output file produced from the Python files. The first file to run is Word_Synth.java, which will create the Hash Tables for the other Java File to access. Word_Synth.java will create 5 different tables in your working directory. You will need to include the text file you want to use as the data set, and place that file in your working directory so the programs can access them.
	The final step for speech processing comes with the Main.java file, which will output to the screen 10 sentences based on the file given to Word_synth.java. Main.java will also produce an output file that will update when the program is run again, generating new files on each successful run. 
	
Problems:
	Currently, the Java Files have an odd error in which the sentence generator accesses an array index that is not available; we could not resolve the issue in time for delivery.
	Posting tweets: Due to the restrictions twitter imposes on their API, this can only post to the account the login tokens are related to,in our case, the fake Trump account. This code is currently running into a 404 error "account does not exist". We tried to work around this but have determined this is an issues on twitter's side that cannot be remedied by adjusting our code.
