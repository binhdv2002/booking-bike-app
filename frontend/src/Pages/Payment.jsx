import React, { useState } from 'react'
import SubHeader from '../Components/SubHeader'
import { Form, Input, Button, Modal, Spin } from 'antd'
import { useNavigate, useParams } from 'react-router-dom'
import axios from 'axios'

export default function PayMent() {
  const navigate = useNavigate();
  const [isShowNow, setIsShowNow] = useState(false)
  const [bikeInfo, setBikeInfo] = useState(null);
  // const [barcode, setBarcode] = useState(null);
  const [showBikeDetail, setShowBikeDetail] = useState(false);
  const { id } = useParams()

  const handleSubmit = (values) => {
    axios.post('http://localhost:8080/api/credit-card/createCard', {
      idBike: id, cardNumber: values.cardcode,
      expiredDate: values.expiration, cvv: values.password
    })
      .then(res => {
        console.log(res)
        setIsShowNow(true)
        axios.post(`http://localhost:8080/api/credit-card/getUser?idCard=7`)
          .then(response => {
            console.log(response)
          })
        axios
          .get(`http://localhost:8080/api/bike/detailBike/${id}`)
          .then((response) => {
            setBikeInfo(response.data); // Lưu thông tin xe đạp vào state
            setIsShowNow(true); // Hiển thị Modal
          })
          .catch(err => console.error(err))

      })
      .catch(err => console.error(err))
  }
  const handleCancel = () => {
    localStorage.removeItem('information')
    window.history.back()
  }

  console.log(bikeInfo);

  const handleGoHome = () => {
    // window.location.href = 'http://localhost:3000'
    navigate('/')
  };

  const handleTransaction = () => {
    axios
      .get(`http://localhost:8080/api/bike/detailBike/${id}`)
      .then(response => {
        const barcode = response.data.barcode;

        return axios.post('http://localhost:8080/api/bike-rental/rentalBike', {
          idUser: '7',
          barcode: barcode,
        });
      })
      .then(response => {
        console.log(response.data);
        setIsShowNow(true);
        localStorage.setItem("renting", JSON.stringify(response.data));
      })
      .catch(error => {
        console.error(error);
      });
  };

  return (
    <>
      <div className='max-w-5xl mx-auto'>
        <SubHeader title={"PayMent"} />
        <div className='max-w-2xl mx-auto border-2 border-gray-400 rounded-lg px-[100px] py-10 mt-5'>
          <Form onFinish={handleSubmit} autoComplete='off' className='flex flex-col gap-4'>
            <Form.Item
              name={"cardcode"}
              rules={[
                {
                  required: true,
                  message: "Please enter the cardcode",
                }
              ]}
              className='mb-0'
              label="Card code"
              labelCol={{ span: 24 }}  // Đặt label trên một dòng
              wrapperCol={{ span: 24 }} // Đặt input trên một dòng

            >
              <Input />
            </Form.Item>
            { }
            <Form.Item
              name={"expiration"}
              rules={[
                {
                  required: true,
                  message: "Please enter the expiration",
                }
              ]}
              className='mb-0'
              label="Expiration date"
              labelCol={{ span: 24 }}  // Đặt label trên một dòng
              wrapperCol={{ span: 24 }} // Đặt input trên một dòng

            >

              <Input
                type='date'
              />
            </Form.Item>
            <Form.Item
              name={"password"}
              rules={[
                {
                  required: true,
                  message: "Please enter your password",
                  whitespace: true
                }
              ]}
              label="Security code"
              labelCol={{ span: 24 }}  // Đặt label trên một dòng
              wrapperCol={{ span: 24 }} // Đặt input trên một dòng

            >
              <Input.Password
              />
            </Form.Item>
            <div className='flex justify-center gap-8'>
              <Button
                onClick={handleCancel}
              >
                Cancel
              </Button>
              <Form.Item className='mb-0'>
                <Button
                  htmlType="submit"
                >
                  Submit
                </Button>
              </Form.Item>
            </div>
          </Form>
        </div>
      </div>
      <Modal
        open={isShowNow}
        onCancel={() => setIsShowNow(false)}
        footer={[
          <Button key="cancel" className='hidden'>
            Hủy
          </Button>,
          <Button key="OK" type="primary" className='hidden'>
            OK
          </Button>,
        ]}
        title={"Create credit card successfully!"}
      >
        <br />
        <div onClick={handleTransaction} className='flex justify-center'>
          <Button onClick={() => setShowBikeDetail(true)}>Rent now</Button>
        </div>
      </Modal>
      <Modal
        visible={showBikeDetail}
        onCancel={() => setShowBikeDetail(false)}
        footer={null}
        title={'Bike Detail'}
      >
        {bikeInfo ? (
          <div>
            <p>ID: {bikeInfo.id}</p>
            <p>Bike Station:</p>
            <p>ID: {bikeInfo.bikeStation.id}</p>
            <p>Name: {bikeInfo.bikeStation.name}</p>
            {/* Continue to display other bike information */}
            <div className="flex justify-center mt-4">
              <Button onClick={handleGoHome}>Back to home</Button>
            </div>
          </div>
        ) : (
          <Spin />
        )}
      </Modal>

    </>
  )
}
