####### REQUIRED IMPORTS FROM THE PREVIOUS ASSIGNMENT #######
from my_package.model import InstanceSegmentationModel
from my_package.data import Dataset
from my_package.analysis import plot_visualization
from my_package.data.transforms import FlipImage, RescaleImage, BlurImage, CropImage, RotateImage
####### ADD THE ADDITIONAL IMPORTS FOR THIS ASSIGNMENT HERE #######
from tkinter import *
from PIL import Image, ImageTk
from tkinter import filedialog
from functools import partial

originalImageLabel = None 
imageLabel = None 

# Define the function you want to call when the filebrowser button is clicked.
def fileClick(clicked, dataset, segmentor):
    ####### CODE REQUIRED (START) #######
    # This function should pop-up a dialog for the user to select an input image file.
    # Once the image is selected by the user, it should automatically get the corresponding outputs from the segmentor.
    # Hint: Call the segmentor from here, then compute the output images from using the `plot_visualization` function and save it as an image.
    # Once the output is computed it should be shown automatically based on choice the dropdown button is at.
    # To have a better clarity, please check out the sample video.
    root.filename = filedialog.askopenfilename(initialdir = "./data/imgs/",title = "Select file",filetypes = (("jpeg files","*.jpg"),("all files","*.*")))
    e.delete(0, END)
    e.insert(0, root.filename)
    dict = dataset[int(root.filename[-5])]
    image = dict["image"]
    pred_boxes, pred_masks, pred_class, pred_score = segmentor(image)
    plot_visualization(image, pred_boxes, pred_class, pred_masks, pred_score, clicked)
    ####### CODE REQUIRED (END) #######
    return

# `process` function definition starts from here.
# will process the output when clicked.
def process(clicked):
    ####### CODE REQUIRED (START) #######
    # Should show the corresponding segmentation or bounding boxes over the input image wrt the choice provided.
    # Note: this function will just show the output, which should have been already computed in the `fileClick` function above.
    # Note: also you should handle the case if the user clicks on the `Process` button without selecting any image file.
    if(e.get() == ""):
        e.insert(0, "Select an Image First...")
    else:
        global originalImageLabel
        global imageLabel
        if(originalImageLabel != None):
            originalImageLabel.destroy()
        originalImage = ImageTk.PhotoImage(Image.open(root.filename))
        originalImageLabel = Label(image=originalImage)
        originalImageLabel.image_names = originalImage
        originalImageLabel.grid(row=1, column=0)
        if(imageLabel != None):
            imageLabel.destroy()
        if(clicked.get() == "Bounding-box"):
            image = Image.open("./output/imgs/Bounding-box_out.png")
            image = ImageTk.PhotoImage(image)
            imageLabel = Label(image=image)
            imageLabel.image_names = image
            imageLabel.grid(row=1, column=1)
        if(clicked.get() == "Segmentation"):
            image = Image.open("./output/imgs/Segmentation_out.png")
            image = ImageTk.PhotoImage(image)
            imageLabel = Label(image=image)
            imageLabel.image_names = image
            imageLabel.grid(row=1, column=1)
    return 
    ####### CODE REQUIRED (END) #######

    # `main` function definition starts from here.
if __name__ == '__main__':

    # CODE REQUIRED (START) ####### (2 lines)
    # Instantiate the root window.
    # Provide a title to the root window.
    root = Tk()
    root.title("Image Segmentation")
    ####### CODE REQUIRED (END) #######

    # Setting up the segmentor model.
    annotation_file = './data/annotations.jsonl'
    transforms = []
    # Instantiate the segmentor model.
    segmentor = InstanceSegmentationModel()
    # Instantiate the dataset.
    dataset = Dataset(annotation_file, transforms=transforms)

    # Declare the options.
    options = ["Segmentation", "Bounding-box"]
    clicked = StringVar()
    clicked.set(options[0])

    e = Entry(root, width=120)
    e.grid(row=0, column=0)
    e.insert(0, "")

    ####### CODE REQUIRED (START) #######
    # Declare the file browsing button
    my_btn = Button(root, text="Browse", command=lambda: fileClick(clicked, dataset, segmentor))
    my_btn.grid(row=0, column=1)
    ####### CODE REQUIRED (END) #######

    ####### CODE REQUIRED (START) #######
    dropdown = OptionMenu(root, clicked, *options)
    dropdown.grid(row=0, column=2)
    ####### CODE REQUIRED (END) #######

    # This is a `Process` button, check out the sample video to know about its functionality
    myButton = Button(root, text="Process", command=partial(process, clicked))
    myButton.grid(row=0, column=3)

    # CODE REQUIRED (START) ####### (1 line)
    root.mainloop()
    ####### CODE REQUIRED (END) #######