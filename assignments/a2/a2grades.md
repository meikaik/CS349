# CS349 A2
Student: mkkoh
Marker: Gregory Philbrick


Total: 57 / 60 (95.00%)

Code: 
(CO: won’t compile, CR: crashes, FR: UI freezes/unresponsive, NS: not submitted)


Notes: 
-Only Model should call notifyObservers.

## REQUIREMENTS

1. [3/3] The main user interface follows the specifications regarding size and look and feel.

2. [8/8] In the drawing (insert) mode, a mouse drag event draws a shape on the canvas.

3. [8/8] In the selection mode, the user can click on an existing shape to select it for modification.

4. [2/2] Only one shape can be selected at once.

5. [2/2] When a shape is selected, the application must provide a visual indication of selection.

6. [2/2] Shape: the toolbar must have a a drop-down menu that allows the user to select the type of shape to draw next (freeform, straight line, rectangle, or ellipse).

7. [2/2] Menu File -> New will allow users to draw on a blank canvas, i.e., it will clear the current contents of the canvas.

8. [2/2] Menu File -> Exit will close the application.

9. [1/2] Menu Edit -> Selection Mode (and similar tool bar button) should change the editor into the Selection mode.

-1 There should be an indication of current editor mode in Edit menu and in the toolbar.

10. [2/2] Menu Edit -> Drawing Mode (and similar tool bar button) should change the editor into the Drawing mode.

11. [2/2] Menu Edit -> Delete should delete the currently selected shape.

12. [6/6] Menu Edit -> Transform will show a dialog window that allows the user to transform the currently selected shape (translation, scaling, and rotation).

13. [3/3] Menu Format -> Stroke width (and similar tool bar widget) is used to set/change the stroke of a shape.

14. [3/3] Menu Format -> Fill color (and similar tool bar widget) is used to set/change the fill color of a closed shape (rectangle/ellipse).

15. [3/3] Menu Format -> Stroke color (and similar tool bar widget) is used to set/change the stroke color of a shape.

## MVC REQUIREMENTS [3/5]

-2 method notifyObservers() is being called by the Controller/view (it should be called from within the Model's methods)

## ENHANCEMENT [3/3]

## GENERAL [2/2] (can be negative)

