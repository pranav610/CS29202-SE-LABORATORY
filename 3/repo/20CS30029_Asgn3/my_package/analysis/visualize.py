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
  image = np.transpose(image, (1, 2, 0)) # transpose the image to (H, W, 3)
  h,w,l = np.shape(image)
  image = image*255 # scale the values to [0, 255]
  image = image.astype(np.uint8) # convert the values to uint8

  # approach withous using blending
  # for box in range(numBBoxes):
  #   image[pred_masks[box][0] >= 0.5] = np.array([0,255,0])

  image = Image.fromarray(image) # convert the image to PIL format

  font = ImageFont.truetype("./arial.ttf", 20) # create a font object
  color = ["red", "green", "blue"] # create a list of colors
  
  for box in range(numBBoxes):
    draw = ImageDraw.Draw(image) # create a draw object
    draw.rectangle(bboxes[box], outline = 'red') # draw the bounding box
    draw.text(bboxes[box][0], pred_class[box]+" "+str(pred_score[box]), font = font) # draw the class label
    mask = pred_masks[box][0] # get the mask
    mask = mask*255 # scale the values to [0, 255]  
    mask = mask.astype(np.uint8) # convert the values to uint8
    masked = Image.fromarray(mask) # convert the mask to PIL format
    masked = ImageOps.colorize(masked, black=(128,128,128), white=color[box]) # colorize using the mask
    image = Image.blend(image, masked, 0.25) # blend the image with the mask

  image.save(outputPath+'/'+str(imgNum)+'_out.png') # save the image

  return image