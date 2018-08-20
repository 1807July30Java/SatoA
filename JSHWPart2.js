/*
1. USA
Define function getUSA()

Find the html element that contains "USA".

Print that element's contents.
*/
var getUSA = function () {
    var spanElements = document.getElementsByTagName("span");
    for(var i = 0; i<spanElements.length; i++){
        if(spanElements[i].innerHTML=="USA"){
            console.log(spanElements[i].innerHTML);
        }
    }
};
/*
2. Sales

Define function getPeopleInSales()

Print the names of all the people in the sales department.
*/
//YOU CAN DO THIS SAM!
//find all td's. check each td if it is sales, then console.log 
//the previousElementSibiling which is the td that contains the name
var getPeopleInSales = function () {
    var tds = document.getElementsByTagName("td");
    for (var i = 0; i < tds.length; i++) {
        if (tds[i].innerHTML == "Sales") {
            console.log(tds[i].previousElementSibling.innerHTML);
        }
    }
};
/*
3. Click Here

Define function getAnchorChildren()

Find all anchor elements with a <span> child.

Print the contents of <span>
*/
var getAnchorChildren = function () {
    var spans = document.getElementsByTagName("span");
    var anchors = document.getElementsByTagName("a");
    for(var i = 0; i<anchors.length;i++){
        for(var j=0;j<spans.length;j++){
            if(anchors[i].contains(spans[j])){
                console.log(spans[j].innerHTML);
            }
        }
    }
};
/*
4. Hobbies

Define function getSkills()

Find all checked options in the 'skills' select element.

Print the value and the contents.
*/
var getSkills = function () {
    var selectElements = document.getElementsByTagName("select");
    for(var i = 0; i<selectElements.length; i++){
        for(var j=0; j<selectElements[i].children.length; j++){
            if(selectElements[i].name == "skills" && selectElements[i].children[j].selected==true){
                console.log(selectElements[i].children[j].innerHTML);
            }
        }
    }
};
/*
5. Custom Attribute

Define function getCustomAttribute()

Find all elements with "data-customAttr" attribute

Print the value of the attribute.

Print the element that has the attribute.
*/
var getCustomAttribute = function () {

};

/*
6. Sum Event

NOTE: Write unobtrusive Javascript

Regarding these elements:

<input id="num1" class="nums" type="text" />

    <input id="num2" class="nums" type="text" />

    <h3>Sum: span id="sum"></span></h3 >


        Define onchange event handler.

            Add < input > element values.

Put the sum in the < span > element.

If values cannot be added, put "Cannot add" in the < span > element
*/



/*
7. Skills Event

NOTE: Write unobtrusive Javascript

When user selects a skill, create an alert with a message similar to:

"Are you sure CSS is one of your skills?"

NOTE: no alert should appear when user deselects a skill.
*/




/*
8. Favorite Color Event

NOTE: Write unobtrusive Javascript

NOTE: This is regarding the favoriteColor radio buttons.

When a user selects a color, create an alert with a message similar to:

"So you like green more than blue now?"

In this example, green is the new value and blue is the old value.

Make the background color(of all favoriteColor radio buttons)
the newly selected favoriteColor
*/




/*
9. Show / Hide Event

NOTE: Write unobtrusive Javascript

When user hovers over an employees name:

Hide the name if shown.
    Show the name if hidden.
*/



/*
10. Current Time

Regarding this element:
<h5 id="currentTime"></h5>

Show the current time in this element in this format: 9: 05: 23 AM

The time should be accurate to the second without having to reload the page.
*/




/*
11. Delay
Regarding this element:

<p id="helloWorld">Hello, World!</p>

Three seconds after a user clicks on this element, change the text to a random color.
*/
var changingWorld = document.getElementById("helloWorld");
changingWorld.addEventListener("click",function(){
    setInterval(function(){
        changingWorld.style.color = "green";
    },3000);
});



/*
12. Walk the DOM

Define function walkTheDOM(node, func)

This function should traverse every node in the DOM.
Use recursion.

On each node, call func(node).
*/
var walkTheDOM = function(node,func){
    var func = function(node){
        node.children.forEach(element => {
            func(element);
        });
    }
}
