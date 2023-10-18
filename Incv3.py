from flask import Flask, request, jsonify
import os
import tensorflow as tf
from tensorflow.keras.preprocessing import image
import numpy as np
from PIL import Image
import base64
from io import BytesIO
import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

app = Flask(__name__)

# Firebase sertifikası
cred = credentials.Certificate("appdatabase.json")
firebase_admin.initialize_app(cred)
db = firestore.client()

# Yüklü model
loaded_model = tf.keras.models.load_model('inceptionv3_model.h5')

@app.route('/process', methods=['POST'])
def process_data():
    data = request.json
    base64_photo = data.get('photoByte64')
    user_id = data.get('userID')
    report_id = data.get('reportID')
    
    test_image = Image.open(BytesIO(base64.b64decode(base64_photo)))
    test_image = test_image.resize((256, 256))

    test_image = image.img_to_array(test_image)
    test_image = np.expand_dims(test_image, axis=0)

    test_image = test_image / 255.0

    predictions = loaded_model.predict(test_image)

    labels = ['false', 'true']
    predicted_label = labels[np.argmax(predictions)]

    # Firebase işlemleri
    report_ref = db.collection("User").document(user_id).collection("Reports").document(report_id)

    snapshot = report_ref.get()
    if snapshot.exists:
        data = snapshot.to_dict()
        report_ref.update({"reportDetail": predicted_label})
        print(predicted_label)
    else:
        print(predicted_label)
        print(user_id)
        print(report_id)
        print("Belge bulunamadı.")


    return jsonify({"message": "Process completed"})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
