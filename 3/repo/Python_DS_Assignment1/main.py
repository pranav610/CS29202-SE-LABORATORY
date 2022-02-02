#Imports
from my_package.model import InstanceSegmentationModel
from my_package.data import Dataset
from my_package.analysis import plot_visualization
from my_package.data.transforms import FlipImage, RescaleImage, BlurImage, CropImage, RotateImage
import numpy as np
from PIL import Image

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
        plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, idx, outputs)    


    #Do the required analysis experiments.
    analysisTask(annotation_file, segmentor, outputs+"/analysis")    

def analysisTask(annnotation_file, segmentor, outputs):
    dataset = Dataset(annnotation_file)
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 0, outputs)

    dataset= Dataset(annnotation_file, [FlipImage()])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 1, outputs)

    dataset= Dataset(annnotation_file, [BlurImage(3)])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 2, outputs)

    dataset= Dataset(annnotation_file, [RescaleImage((image.height*2, image.width*2))])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 3, outputs)

    dataset= Dataset(annnotation_file, [RescaleImage(image.height//2, image.width//2)])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 4, outputs)

    dataset= Dataset(annnotation_file, [RotateImage(-90)])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 5, outputs)

    dataset= Dataset(annnotation_file, [RotateImage(45)])
    data = dataset[9]
    image = data["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    image=plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, 6, outputs)

def main():
    segmentor = InstanceSegmentationModel()
    experiment('./data/annotations.jsonl', segmentor, [FlipImage(), BlurImage(3)], None) # Sample arguments to call experiment()


if __name__ == '__main__':
    main()
