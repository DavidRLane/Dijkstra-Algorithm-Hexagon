import json
import re
import os

filenames = ['condensed_2009.json', 'condensed_2010.json', 'condensed_2011.json', 'condensed_2012.json', 'condensed_2013.json', 'condensed_2014.json', 'condensed_2015.json', 'condensed_2016.json', 'condensed_2017.json']
polfilenames = ['condensed_2015.json', 'condensed_2016.json', 'condensed_2017.json']
pathhh = 'C:\\Users\\tiger_000\\Documents\\College HW\\CS582\\Trumptwitter\\'
count = 0

for file in filenames:
	with open(os.path.join(pathhh, file)) as data_file:
		data = json.load(data_file)
		for tweet in data:
			RT = tweet["text"].startswith("-") or re.search("RT ", tweet["text"])
			if RT:
				continue
			Reply = re.search(r"(?i)@realDonaldTrump", tweet["text"]) or re.search(r'(?i)"@', tweet["text"])
			if Reply:
				continue
			tweet["text"] = re.sub(r"http\S+", "", tweet["text"])
			tweet["text"] = re.sub(r"amp;", "", tweet["text"])
			tweet["text"] = re.sub(r"\n", " ", tweet["text"])
#			tweet["text"] = re.sub(r".", " ", tweet["text"])
			print "<s> %s </s>" % (tweet["text"].encode("ascii", "ignore"))
			count = count + 1
print count