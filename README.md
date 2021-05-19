# TreeFactorials
Tree Factorials in Java Language

**Introduction**

In this lab you will create a Tree fractal which uses recursions.

**ScreenShot**

Scale is the multiplier of the size being drawn.

![TreeFactor](https://user-images.githubusercontent.com/35514180/118866183-bc210400-b8af-11eb-8109-2f22a4a09c5c.jpg)

**Instructions**

This is a bonus lab so majority of the research is left for you.

The idea is to create a method inside of the factorials.model.Tree class which will create what is in the screenshot above. There are other examples of different fractals in the skeleton project. You can use any of the code given to you.

Project Import, Execution, Creation of JAR and ZIP
In the video i mention you need to use "clean install javafx:jlink" for your maven build. You can skip "javafx:jlink".

Just use "clean install" or "clean install test" if you have any junit tests.

Summery - Project import, maven, jar and zip

Bonus
Make the above fractal be drawn animated. +1

Hints
You can ignore all or use any of the hints below.

You don't have to modify anything but the handle method, or adding new methods.
Make shaft of the tree in the handle method as it follows different rules.
To adjust the width of a line use GraphicsContext::setLineWidth.
To draw a line use GraphicsContext::strokeLine.
You will need to create a new method for your recursive method which will be called from handle method.
Create a method that takes depth, starting x and y, length of line to draw, angle of the line to draw, and finally another angle to add or subtract from the line angle.
Don't forget the base case at depth zero.
Calculate the end of the line using dx=length*Math.cos(angle) and dy=length*Math.sin(angle).
dy and dx are distance traveled in x and/or y direction.
you can manipulate the angle by adding or subtracting your last angle argument from your method.
keep in mind your y will always reduce as you go up. x will increase to right and decrease to left.
When calling your method inside of itself don't forget to reduce your length, update your angle (once add, once subtract), and finally reduce your last angle argument (use multiplication).
You will have to call your method inside of itself multiple times as each line splits.
Calculate X and Y components
http://zonalandeducation.com/mstm/physics/mechanics/forces/forceComponents/forceComponents.html

http://zonalandeducation.com/mstm/physics/mechanics/forces/forceComponents/forceComponents.html

Extra
Below is a good example of how how the process should work.

https://natureofcode.com/book/chapter-8-fractals/#85-trees

https://natureofcode.com/book/chapter-8-fractals/#85-trees

Submission
You need to submit two files. One Runnable Jar file and one Zip (archive) of source code. If Brightspace does not allow you to submit a jar file just zip both of your files and submit.

You need to submit a Runnable Jar file of your Lab.
[firstName]-[lastName]-[labSection#].jar
ex: shawn-emami-11.jar
You need to submit a zip file of your source code. 
[firstName]-[lastName]-[labSection#].zip
ex: shawn-emami-11.zip
