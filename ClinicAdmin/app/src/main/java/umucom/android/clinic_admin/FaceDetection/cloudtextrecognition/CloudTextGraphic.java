// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package umucom.android.clinic_admin.FaceDetection.cloudtextrecognition;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.firebase.ml.vision.cloud.text.FirebaseVisionCloudText;

import umucom.android.clinic_admin.FaceDetection.GraphicOverlay;


/**
 * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
 * overlay view.
 */
public class CloudTextGraphic extends GraphicOverlay.Graphic {
  private static final int TEXT_COLOR = Color.WHITE;
  private static final float TEXT_SIZE = 54.0f;
  private static final float STROKE_WIDTH = 4.0f;

  private final Paint rectPaint;
  private final Paint textPaint;
  private final FirebaseVisionCloudText text;
  private final GraphicOverlay overlay;

  CloudTextGraphic(GraphicOverlay overlay, FirebaseVisionCloudText text) {
    super(overlay);

    this.text = text;
    this.overlay = overlay;

    rectPaint = new Paint();
    rectPaint.setColor(TEXT_COLOR);
    rectPaint.setStyle(Paint.Style.STROKE);
    rectPaint.setStrokeWidth(STROKE_WIDTH);

    textPaint = new Paint();
    textPaint.setColor(TEXT_COLOR);
    textPaint.setTextSize(TEXT_SIZE);
    // Redraw the overlay, as this graphic has been added.
    postInvalidate();
  }

  /** Draws the text block annotations for position, size, and raw value on the supplied canvas. */
  @Override
  public void draw(Canvas canvas) {
    if (text == null) {
      throw new IllegalStateException("Attempting to draw a null text.");
    }

    float x = overlay.getWidth() / 4.0f;
    float y = overlay.getHeight() / 4.0f;

    canvas.drawText(text.getText(), x, y, textPaint);
  }
}
