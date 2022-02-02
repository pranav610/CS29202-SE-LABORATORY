#Imports
from PIL import Image

class RescaleImage(object):
    '''
        Rescales the image to a given size.
    '''

    def __init__(self, output_size):
        '''
            Arguments:
            output_size (tuple or int): Desired output size. If tuple, output is
            matched to output_size. If int, smaller of image edges is matched
            to output_size keeping aspect ratio the same.
        '''

        self.output_size = output_size

    def __call__(self, image):
        '''
            Arguments:
            image (numpy array or PIL image)

            Returns:
            image (numpy array or PIL image)

            Note: You do not need to resize the bounding boxes. ONLY RESIZE THE IMAGE.
        '''

        if(isinstance(self.output_size,tuple)):
            outSize = self.output_size
        else:
            if(image.size[0] < image.size[1]):
                outSize = (self.output_size, int(image.size[1] * self.output_size / image.size[0]))
            else:
                outSize = (int(image.size[0] * self.output_size / image.size[1]), self.output_size)
        
        return image.resize(outSize, Image.BILINEAR)
        