#Imports
from my_package.model import InstanceSegmentationModel # image segmentation model
from my_package.data import Dataset # create dataset for the segmentor
from my_package.analysis import plot_visualization # plot the segmentation maps with masks and borders
from my_package.data.transforms import FlipImage, RescaleImage, BlurImage, CropImage, RotateImage # image transforms
import numpy as np # numpy array to store arrays of images
from PIL import Image # image processing library

# matpolt lib import
import matplotlib as mpl 
import matplotlib.pyplot as plt

def experiment(annotation_file, segmentor, transforms, outputs):
    '''
        Function to perform the desired experiments

        Arguments:
        annotation_file: Path to annotation file
        segmentor: The image segmentor
        transforms: List of transformation classes
        outputs: path of the output folder to store the images
    '''
    outputs_ = outputs + "/pred"
    #Create the instance of the dataset.
    dataset = Dataset(annotation_file, transforms)

    #Iterate over all data items.

    for idx in range(len(dataset)):
        #Get the data item.
        data = dataset[idx]

        #Get the image and the annotations.
        image = data['image']

    #Get the predictions from the segmentor.
        pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)

    #Draw the segmentation maps on the image and save them.
        plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, idx, outputs_)    


    #Do the required analysis experiments.
    analysisTask(annotation_file, segmentor, outputs+"/analysis")    

def analysisTask(annnotation_file, segmentor, outputs):
    # Create the instance of the dataset with no transformations and input it in segmentor
    dataset = Dataset(annnotation_file)
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    orgImage=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 0, outputs)
    plot(orgImage,2,'Original') # plot the original image

    # Create the instance of the dataset with flipping and input it in segmentor
    dataset= Dataset(annnotation_file, [FlipImage()])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 1, outputs)
    plot(image,4,'Flipped') # plot the flipped image

    # Create the instance of the dataset with blur and input it in segmentor
    dataset= Dataset(annnotation_file, [BlurImage(3)])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 2, outputs)
    plot(image,5,'Blurred') # plot the blurred image

    # Create the instance of the dataset with rescaling and input it in segmentor
    dataset= Dataset(annnotation_file, [RescaleImage((orgImage.width*2, orgImage.height*2))])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 3, outputs)
    plot(image,6,'Scaled 2X') # plot the rescaled image

    # Create the instance of the dataset with rescaling and input it in segmentor
    dataset= Dataset(annnotation_file, [RescaleImage((orgImage.width//2, orgImage.height//2))])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 4, outputs)
    plot(image,7,'Scaled 0.5X') # plot the rescaled image

    # Create the instance of the dataset with rotation and input it in segmentor
    dataset= Dataset(annnotation_file, [RotateImage(-90)])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 5, outputs)
    plot(image,8,'RightRotated 90deg') # plot the rotated image

    # Create the instance of the dataset with rotation and input it in segmentor
    dataset= Dataset(annnotation_file, [RotateImage(45)])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 6, outputs)
    plot(image,9,'LeftRotated 45deg') # plot the rotated image

    mpl.rcParams['toolbar']='None' # remove the toolbar

    plt.savefig(outputs+"/plot.png") # save the plot

# plot function for adding images in the same plot
def plot(image,region,title): 
    plt.subplot(3,3,region)
    plt.imshow(image)
    plt.title(title)
    plt.axis('off')

# main function
def main():
    segmentor = InstanceSegmentationModel()
    experiment('./data/annotations.jsonl', segmentor, [FlipImage(), BlurImage(3)], "./output") # Sample arguments to call experiment()

# run the main function
if __name__ == '__main__':
    main()
