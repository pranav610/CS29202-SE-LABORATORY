#Imports
from PIL import Image
import numpy as np
import random

class CropImage(object):
    '''
        Performs either random cropping or center cropping.
    '''

    def __init__(self, shape, crop_type='center'):
        '''
            Arguments:
            shape: output shape of the crop (h, w)
            crop_type: center crop or random crop. Default: center
        '''

        self.shape = shape
        self.crop_type = crop_type

    def __call__(self, image):
        '''
            Arguments:
            image (numpy array or PIL image)

            Returns:
            image (numpy array or PIL image)
        '''

        orgH, orgW, channel = image.shape
        cropH, cropW = self.shape

        if(self.crop_type == 'center'):
            return image.crop(((orgW - cropW) // 2, (orgH - cropH) // 2, (orgW + cropW) // 2, (orgH + cropH) // 2))
            
        elif(self.crop_type == 'random'):
            h_start = random.randint(0, orgH - cropH)
            w_start = random.randint(0, orgW - cropW)
            return image.crop((w_start, h_start, w_start + cropW, h_start + cropH))

        

 