#Imports
import json
from PIL import Image
import numpy as np
from my_package.data.transforms import RescaleImage, RotateImage, CropImage, BlurImage, FlipImage

class Dataset(object):
    '''
        A class for the dataset that will return data items as per the given index
    '''

    def __init__(self, annotation_file, transforms = None):
        '''
            Arguments:
            annotation_file: path to the annotation file
            transforms: list of transforms (class instances)
                        For instance, [<class 'RandomCrop'>, <class 'Rotate'>]
        '''
        self.transforms = transforms
        with open(annotation_file, 'r') as f:
            self.dataList = list(f)


    def __len__(self):
        '''
            return the number of data points in the dataset
        '''
        return len(self.dataList)

    def __getitem__(self, idx):
        '''
            return the dataset element for the index: "idx"
            Arguments:
                idx: index of the data element.

            Returns: A dictionary with:
                image: image (in the form of a numpy array) (shape: (3, H, W))
                gt_png_ann: the segmentation annotation image (in the form of a numpy array) (shape: (1, H, W))
                gt_bboxes: N X 5 array where N is the number of bounding boxes, each 
                            consisting of [class, x1, y1, x2, y2]
                            x1 and x2 lie between 0 and width of the image,
                            y1 and y2 lie between 0 and height of the image.

            You need to do the following, 
            1. Extract the correct annotation using the idx provided.
            2. Read the image, png segmentation and convert it into a numpy array (wont be necessary
                with some libraries). The shape of the arrays would be (3, H, W) and (1, H, W), respectively.
            3. Scale the values in the arrays to be with [0, 1].
            4. Perform the desired transformations on the image.
            5. Return the dictionary of the transformed image and annotations as specified.
        '''
        if(idx>=len(self.dataList)):
            print("Index out of bound")
            return None

        # 1.
        annotation = json.loads(self.dataList[idx]) # loads the corrsponding idx as a dictionary

        # 2.
        image = Image.open("./data/"+annotation['img_fn'])
        print("./data/"+annotation['img_fn'])
        gt_png_ann = Image.open("./data/"+annotation['png_ann_fn'])

        if self.transforms:
            for transform in self.transforms:
                image = transform(image)
                gt_png_ann = transform(gt_png_ann)

        npImage = np.array(image)
        npImage = np.transpose(npImage, (2,0,1)) 
        
        np_gt_png_ann = np.array(gt_png_ann)
        np_gt_png_ann = np.transpose(gt_png_ann,(2,0,1))

        # 3. 
        npImage = npImage / 255.0
        np_gt_png_ann = np_gt_png_ann/ 255.0
        
        # 5. Return the dictionary of the transformed image and annotations as specified.
        return {'image': npImage, 'gt_png_ann': np_gt_png_ann, 'gt_bboxes': annotation['bboxes']}