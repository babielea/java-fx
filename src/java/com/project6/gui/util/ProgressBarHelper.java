package com.project6.gui.util;

import javafx.scene.control.ProgressBar;

/**
 * Created by e.aslan on 20.06.2016.
 */
public class ProgressBarHelper {
  private static ProgressBarHelper ourInstance = new ProgressBarHelper();

  private static ProgressBar progressBar;

  public static ProgressBarHelper getInstance() {
    return ourInstance;
  }

  private ProgressBarHelper() {
  }

  public static ProgressBar getProgressBar() {
    return progressBar;
  }

  public static void setProgressBar(ProgressBar progressBar) {
    ProgressBarHelper.progressBar = progressBar;
  }
}
