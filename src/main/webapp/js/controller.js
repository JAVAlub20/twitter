var articleApp = angular.module('articleApp', []);
console.log("tesgt");
articleApp.controller('articleController', function ($scope, $http){
        $scope.init = function (){
            $http({
                method: 'GET',
                url: 'rest/articles/',
                params: {}
            }).then(
                function (response){
                    $scope.articles = response.data;
                }
            );
        };
    $scope.articleById = function (articleId){
        console.log(articleId);
        $http({
            method: 'GET',
            url: 'rest/articles/' + articleId,
            params: {}
        }).then(
            function (response){
                $scope.article = response.data;
            }
        );
    };


    }
);
