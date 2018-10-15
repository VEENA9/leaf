package com.veena.senz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//

//import com.milcanx.depom.ShowAd;
//import com.milcanx.depom.metodlar;
//import com.milcanx.toastlar.toastkodlar;

public class exphoto extends AppCompatActivity {
    private static final int CAMERA_REQUET = 123;
    Button btn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exphoto);
        btn = (Button) findViewById(R.id.idbtn);

        imageView = (ImageView)  findViewById(R.id.imageViewaa);
    }

    public void btnClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUET);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUET && requestCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(Bitmap.createScaledBitmap(photo, 300, 300, false));
        }
    }


}



    /*
    requestCode: Intent when starting the variables that we were sent is located.
    If you requestcode to take a picture with the following code as shown in code 1 processes that take place
    If 2 will take place in the video capture process.
    */

        /*
        resultCode: we have carried out is completed and helps us understand the process can not be completed.
        As shown, the value of resultcode result_ok if the result of the process is ok and is passed to the other stages.
         */
//        VideoView video;
//        ImageView img_view;
//        public static int TAKE_PICTURE = 1;
//        public static int CAPTURE_VIDEO = 2;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_exphoto);
//
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//            //I have added to the linearlayout for advertising :)
//            LinearLayout rek;
//            rek = (LinearLayout) findViewById(R.id.reklam_cek);
//            new ShowAd(this, rek);
//
//
////            = (VideoView) findViewById(R.id.video_view);
//            img_view = (ImageView) findViewById(R.id.image_view);
//
//            Button imagebutton = (Button) findViewById(R.id.btnfotograf);
//            assert imagebutton != null;
//            imagebutton.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // Shooting to start the event, we call its components the function of the intent in Android
//                    Intent picture_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(picture_intent, TAKE_PICTURE); // We send our request code along with the intent and launch activity
//
//
//                }
//            });
//
//            Button videobutton = (Button) findViewById(R.id.btnkamera);
//            assert videobutton != null;
//            videobutton.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // capture video to start the event, we call its components the function of the intent in Android
//                    Intent video_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                    startActivityForResult(video_intent, CAPTURE_VIDEO);
//                    // sended  intent with requestcode  to the activity  and start activity
//                }
//            });
//
//            // the button to show the codes
////            Button kod = (Button) findViewById(R.id.kamerabtn_kod);
////            assert kod != null;
////            kod.setOnClickListener(new View.OnClickListener() {
////
////                @Override
////                public void onClick(View v) {
////                    metodlar.javaurlekle(23);
////
////                }
////            });
//
//        }
//
//
//        //data: type of  intent created a variable that corresponds to the type that are run.
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (resultCode != RESULT_OK)
//                return;
//            if (requestCode == 2) {
//
//                video.setVisibility(View.VISIBLE);
//                img_view.setVisibility(View.GONE);
//                assert video != null;
//                video.setVideoURI(data.getData());
//                video.setMediaController(new MediaController(getApplicationContext()));
//                video.requestFocus();
//                video.start();
//
//            } else if (requestCode == 1) {
//
//                video.setVisibility(View.GONE);
//                img_view.setVisibility(View.VISIBLE);
//                Bundle extras = data.getExtras();
//                Bitmap bitmap_image = (Bitmap) extras.get("data");
//                assert img_view != null;
//                img_view.setImageBitmap(bitmap_image);
//            }
//
//        /*requestCode 1 Let's look at the actual process,
//        With the help of the bundle, returning variables from the application, ekstra saklanıyor
//        then returning this variable, On the type of Bitmap is converted
//        use setImageBitmap,To view it in Imageview
//        We have seen the photograph we took on this.*/
//
//
//        /*requestCode 1 Let's look at the actual process, After creating VideoView, we get our video, we set uri object.
//        We also specify the MediaController class, which is required to play your video.
//         Then we start the video with the start function defined in VideoView.*/
//
//
//        /*So if we want an activity to return a result, we must use the StartActivityForResult function.
//        According to the returning result, we can determine the operations to be done and it will be easier to implement..
//        We can do this in many processes other than taking pictures and video..*/
//
//
//        }}
//
////        @Override
////        public boolean onOptionsItemSelected(MenuItem item) {
////            int id = item.getItemId();
////
////            if (id == android.R.id.home) {
////                VideoKamera.this.finish();
////                return true;
////            }
////            return super.onOptionsItemSelected(item);
////        }
////    }