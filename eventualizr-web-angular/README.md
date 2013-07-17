README
======

## Possible Error messages during build
#### Message (during mvn clean install):
`'testacular' is not recognized as an internal or external command`

#### Fix:
make sure you have NodeJS installed, then install testacular globally via node package manager (npm):
`npm install -g testacular`

#### Message (during mvn clean install):
`uncalled type error cannot find property name of null`

#### Fix:
This means you'll have an older version of testacular globally installed. Fix it by reinstalling testacular via node package manager (npm):
`npm install -g testacular`

#### Message (during mvn clean install):
When in your e2e-conf, you have defined a proxy, and get the message `/ is proxied, you should probably change urlroot to avoid conflicts`

#### Fix:
In e2e-conf: `urlroot='/_karma_/'`
For the deets, check [StackOverflow](http://stackoverflow.com/questions/15665960/can-do-i-use-testacular-to-test-web-pages-that-are-not-not-on-my-localhost)