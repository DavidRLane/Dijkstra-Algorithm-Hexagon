import re
from twitter import Twitter, OAuth, TwitterHTTPError, TwitterStream


ACCESS_TOKEN = '852590894746185728-e9OccGX3TYklo1hewI2VkSr3yk42ww1'
ACCESS_SECRET = 'Zxmlz8w7NUhJLmUICIJZDDyfxFuWTRTTSf2yUvbECDEcH'
CONSUMER_KEY = 'MqncUSd6UhmnRCB6hBQobAbBX'
CONSUMER_SECRET = 'Ps8GL7Cfsl3jFgoQYrxEzI7YqlcL4tFp81Vxb7nCUb3ctRNTgx'

twitter = Twitter(auth = OAuth(ACCESS_TOKEN, ACCESS_SECRET, CONSUMER_KEY, CONSUMER_SECRET))

user = 'DonaldTrump582'

output = open('C:\\Users\\tiger_000\\Desktop\\Trump Twitter Output.txt')

for line in output:
	line = filter(lambda i: not str.isdigit(i), line)
	line =  re.sub(re.escape(") "), "", line)
	twitter.update_status(status = line)
	print "updated status: %s" % line
