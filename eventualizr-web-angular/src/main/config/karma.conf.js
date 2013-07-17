basePath = '../';

files = [
  JASMINE,
  JASMINE_ADAPTER,
  'http://ajax.googleapis.com/ajax/libs/angularjs/1.0.6/angular.min.js',
  'http://ajax.googleapis.com/ajax/libs/angularjs/1.0.6/angular-resource.min.js',
  'http://cdnjs.cloudflare.com/ajax/libs/angular-strap/0.7.2/angular-strap.min.js',
  'webapp/lib/localize/*',
  'test/lib/angular/angular-mocks.js',
  'webapp/js/**/*.js',
  'test/unit/controllersSpec.js'
];

autoWatch = true;

// web server port
port = 8080;

// cli runner port
runnerPort = 9100;

browsers = ['Chrome'];

junitReporter = {
  outputFile: 'test_out/unit.xml',
  suite: 'unit'
};
