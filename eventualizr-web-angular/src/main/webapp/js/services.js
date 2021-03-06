'use strict';

angular.module('eventualizrApp.services', []).
  value('version', '0.1');

angular.module('eventualizrApp.services').factory('Meeting', ['$resource', function($resource){
	return $resource('api/meetings/:meetingId', {meetingId: '@meetingId'});
}]);

angular.module('eventualizrApp.services').factory('Talk', ['$resource', function($resource){
	return $resource('api/meetings/:meetingId/talks/:talkId', {meetingId: '@meetingId', talkId: '@talkId'});
}]);

angular.module('eventualizrApp.services').factory('i18n', function($rootScope, ngI18nResourceBundle, ngI18nConfig){

    function setLanguage(language) {
        loadResourceBundle(language);
        $rootScope.language = language;
    };

    function loadResourceBundle(language){
        ngI18nResourceBundle.get({locale: language}).success(function (resourceBundle) {
            $rootScope.resourceBundle = resourceBundle;
            $rootScope.$broadcast('resourceBundleLoaded', resourceBundle);
        });

    }

    loadResourceBundle(ngI18nConfig.defaultLocale);
    $rootScope.language = ngI18nConfig.defaultLocale;

    return {
        languages: ngI18nConfig.supportedLocales,
        setLanguage: setLanguage
    };
});
