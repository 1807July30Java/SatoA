var homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function(n){
    var fibArray = [0,1];
    for(var i = 2;i<=n;i++){
        fibArray[i] = fibArray[i-1]+fibArray[i-2];
    }
    return fibArray[n]
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function(array) {
    var sorted = array;
    var changed;
    do{
        changed = false;
        for(var i = 1; i<sorted.length;i++){
            if(sorted[i-1]>sorted[i]){
                var mover = sorted[i];
                sorted[i] = sorted[i-1];
                sorted[i-1] = mover;
                changed = true;
            }
        }
    }while(changed);
    return sorted;
};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function(n){
    //start with edge cases
    if(n<0){
        return 0;
    }
    else if(n==0||n==1){
        return 1;
    } else {
        var factoreal = 1;
        for(var i=2;i<=n;i++){
            factoreal = factoreal * i;
        }
        return factoreal;
    }
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
    var rotatedArray = array;
    var removedElements = [];
    for(var i=0; i<n ; i++){
    	removedElements = rotatedArray.slice(0,1);
    	rotatedArray = rotatedArray.slice(1,rotatedArray.length).concat(removedElements);
    }
    return rotatedArray;
};

/*
 5. Balanced Brackets

 A bracket is any one of the following: (, ), {, }, [, or ]

 The following are balanced brackets:
    ()
    ()()
    (())
    ({[]})

 The following are NOT balanced brackets:
 (
 )
 (()
 ([)]

 Return true if balanced
 Return false if not balanced
*/
homework.balancedBrackets = function(bracketsString){
    /*
        Counts for each type of bracket
        Add one for every opening, subtract 1 for each closing
    */
    var bracketC = 0;
    var curlyC = 0;
    var squareC = 0;
    //assuming )( is not a balanced bracket set
    var stringArr = bracketsString.split("");
    for (var i = 0; i< stringArr.length; i++){
        if(stringArr[i]==="("){
            bracketC = bracketC + 1;
        } else if(stringArr[i]=="{"){
            curlyC = curlyC + 1;
        } else if(stringArr[i]=="["){
            squareC = squareC + 1;
        } else if(stringArr[i]==")"){
            bracketC -=1;
        } else if(stringArr[i]=="}"){
            curlyC -= 1;
        } else if(stringArr[i]=="]"){
            squareC -= 1;
        }
        if(bracketC<0||curlyC<0||squareC<0){
            return false;
        }
    }
    if(bracketC==0&&curlyC==0&&squareC==0){
        return true;
    } else {
        return false;
    }
};
