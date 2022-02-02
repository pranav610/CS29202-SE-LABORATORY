#Imports

from torch import imag
from PIL import Image, ImageDraw,ImageFont, ImageOps 
import numpy as np
import matplotlib.pyplot as plt

def plot_visualization(image, bboxes, pred_class, pred_masks, pred_score, imgNum, outputPath): # Write the required arguments
  # The function should plot the predicted segmentation maps and the bounding boxes on the images and save them.
  # Tip: keep the dimensions of the output image less than 800 to avoid RAM crashes
  if(len(pred_class)>3):
    numBBoxes = 3
  else:
    numBBoxes = len(pred_class)

  image = np.transpose(image, (2, 0, 1)) # transpose the image to (H, W, 3)
  image = image*255 # scale the values to [0, 255]
  image = image.astype(np.uint8) # convert the values to uint8
  image = Image.fromarray(image) # convert the image to PIL format

  font = ImageFont.truetype("arial.ttf", 20) # create a font object

  for box in range(numBBoxes):
    abc = [0,0,0]
    draw = ImageDraw.Draw(image) # create a draw object
    draw.rectangle(bboxes[box], outline = 'red') # draw the bounding box
    draw.text(bboxes[box][0], pred_class[box]+str(pred_score[box]), font = font) # draw the class label
    mask = np.transpose(pred_masks[box], (2, 0, 1)) # transpose the mask to (H, W, 1)
    mask = mask*255 # scale the values to [0, 255]  
    mask = mask.astype(np.uint8) # convert the values to uint8
    abc[box] = 1
    mask = ImageOps.colorize(mask, black="black", white=255*abc)
    mask = Image.fromarray(mask) # convert the mask to PIL format
    image = Image.blend(image, mask, 0.3)

  image.save(outputPath+'/'+str(imgNum)+'_out.png') # save the image

  return image