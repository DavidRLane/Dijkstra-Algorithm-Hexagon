# Import the necessary package to process data in JSON format
import re
try:
    import json
except ImportError:
    import simplejson as json

# Import the necessary methods from "twitter" library
from twitter import Twitter, OAuth, TwitterHTTPError, TwitterStream

# Variables that contains the user credentials to access Twitter API 
ACCESS_TOKEN = '852590894746185728-e9OccGX3TYklo1hewI2VkSr3yk42ww1'
ACCESS_SECRET = 'Zxmlz8w7NUhJLmUICIJZDDyfxFuWTRTTSf2yUvbECDEcH'
CONSUMER_KEY = 'MqncUSd6UhmnRCB6hBQobAbBX'
CONSUMER_SECRET = 'Ps8GL7Cfsl3jFgoQYrxEzI7YqlcL4tFp81Vxb7nCUb3ctRNTgx'

twitter = Twitter(auth = OAuth(ACCESS_TOKEN, ACCESS_SECRET, CONSUMER_KEY, CONSUMER_SECRET))

# Initiate the connection to Twitter Streaming API

user = "realDonaldTrump"

new_tweets = twitter.statuses.user_timeline(screen_name = user, count=200)

while len(new_tweets) > 0:
	for tweet in new_tweets:
		RT = re.search("RT ", tweet["text"])
		if RT:
			continue
		tweet["text"] = re.sub(r"http\S+", "", tweet["text"])	
		tweet["text"] = re.sub(r"amp;", "", tweet["text"])
		tweet["text"] = re.sub(r"\n", " ", tweet["text"])
		oldest = tweet["id"]
		if tweet["text"].endswith("..."):
			tweet["text"] = re.sub(r"...","", tweet["text"])
			print "<s> %s " % (tweet["text"].encode("ascii", "ignore")) 
			continue
		if tweet["text"].startswith("..."):
			tweet["text"] = re.sub(r"...","", tweet["text"])
			print "%s </s>" % (tweet["text"].encode("ascii", "ignore"))
			continue
		print "<s> %s </s>" % (tweet["text"].encode("ascii", "ignore"))
	new_tweets = twitter.statuses.user_timeline(screen_name = user, count = 200, max_id = oldest)
