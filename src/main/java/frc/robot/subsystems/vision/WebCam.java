package frc.robot.subsystems.vision;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class WebCam extends SubsystemBase {
  Thread m_visionThread;

  public WebCam() {}

  @Override
  public void periodic() {
    m_visionThread =
        new Thread(
            () -> {
              UsbCamera camera = CameraServer.startAutomaticCapture(0);

              camera.setResolution(640, 480);

              CvSink cvSink = CameraServer.getVideo();

              CvSource outputStream = CameraServer.putVideo("Rectangle", 640, 480);

              Mat mat = new Mat();

              while (!Thread.interrupted()) {

                if (cvSink.grabFrame(mat) == 0) {
                  outputStream.notifyError(cvSink.getError());
                  continue;
                }

                Imgproc.rectangle(
                    mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
                outputStream.putFrame(mat);
              }
            });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
  }
}
