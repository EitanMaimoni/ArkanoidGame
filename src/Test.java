import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

public class Test {

    public void drawRandomLine1() {
        Line[] arr = new Line[23];
        arr[0] = new Line(50, 240, 50, 280);
        arr[1] = new Line(50, 280, 200, 280);
        arr[2] = new Line(50, 240, 200, 280);
        arr[3] = new Line(200, 200, 200, 230);
        arr[4] = new Line(200, 230, 200, 250);
        arr[5] = new Line(200, 170, 200, 200);
        arr[6] = new Line(200, 250, 200, 280);
        arr[7] = new Line(50, 130, 178, 164);
        arr[8] = new Line(178, 164, 200, 170);
        arr[9] = new Line(230, 150, 230, 250);
        arr[10] = new Line(50, 130, 300, 100);
        arr[11] = new Line(20, 30, 178, 164);
        arr[12] = new Line(250, 270, 250, 50);
        arr[13] = new Line(0, 207, 400, 207);
        arr[14] = new Line(250, 50, 300, 50);
        arr[15] = new Line(300, 50, 340, 50);
        arr[16] = new Line(340, 50, 380, 50);
        arr[17] = new Line(125, 45, 178, 84);
        arr[18] = new Line(178, 84, 200, 90);
        arr[19] = new Line(260, 110, 310, 150);
        arr[20] = new Line(310, 150, 360, 190);
        arr[21] = new Line(260, 290, 310, 250);
        arr[22] = new Line(310, 250, 360, 210);

        GUI gui = new GUI("title", 700, 700);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();


        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 23; i++) {
            drawLine(arr[i], d);
            //middle point.
            drawMiddlePoint(arr[i], d);
        }
        for (int i = 0; i < 23; i++) {
            for (int j = i + 1; j < 23; j++) {
                //interaction point.
                drawIntersectionPoint(arr[i], arr[j], d);
            }
        }
        gui.show(d);

    }


    public void drawLine(Line line, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) line.start().getX(), (int) line.start().getY(),
                (int) line.end().getX(), (int) line.end().getY());

    }


    public void drawMiddlePoint(Line line, DrawSurface d) {
        Point middle = line.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
    }

    public void drawIntersectionPoint(Line l1, Line l2, DrawSurface d) {
        // check if there is an intersection point.
        Point intersection = l1.intersectionWith(l2);
        // draw if there is intersection point.
        if (intersection != null) {
            d.setColor(Color.RED);
            d.fillCircle((int) intersection.getX(),
                    (int) intersection.getY(), 3);

        }
    }

    public static void main(String[] args) {
        Test example = new Test();
        example.drawRandomLine1();
    }
}
