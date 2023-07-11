package com.example.clotclassifier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clotclassifier.ml.ModelUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;


public class HomeFragment extends Fragment {
    Button uploadpic, predictbutton;
    ImageView imageselected;
    TextView result;
    Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_home, container, false);

       uploadpic = view.findViewById(R.id.button2);
       imageselected = view.findViewById(R.id.imageView);
       predictbutton = view.findViewById(R.id.predictbtn);
       result = view.findViewById(R.id.result);

       uploadpic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.setAction(Intent.ACTION_GET_CONTENT);
               intent.setType("image/*");
               startActivityForResult(intent,1);
           }
       });
       predictbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



               try {
                   ModelUnquant model = ModelUnquant.newInstance(view.getContext());

                   // Creates inputs for reference.
                   TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);


                   bitmap = Bitmap.createScaledBitmap(bitmap, 224,224, true);
                   inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());

                   // Runs model inference and gets result.
                   ModelUnquant.Outputs outputs = model.process(inputFeature0);
                   TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                   //Printing the result in a TextView
                  result.setText(getMax(outputFeature0.getFloatArray())+" ");

                   // Releases model resources if no longer used.
                   model.close();
               } catch (IOException e) {
                   // TODO Handle the exception
               }

           }
       });

        return view;
    }
    int getMax(float[] arr){
        int max = 0;
        for(int i = 0; i<arr.length; i++){
            if(arr[i] > arr[max]) max = i;
        }
        return max;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != 1){
            Toast.makeText(getActivity(), "Image Selected", Toast.LENGTH_SHORT).show();
            if(data!=null){
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                    imageselected.setImageBitmap(bitmap);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}