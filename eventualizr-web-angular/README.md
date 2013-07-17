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

