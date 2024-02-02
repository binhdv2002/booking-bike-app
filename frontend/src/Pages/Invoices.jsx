import React, { useEffect, useState } from "react";

import SubHeader from "../Components/SubHeader";
import { Form, Input, Button, Modal, Spin } from "antd";
import { useParams } from "react-router-dom";
import axios from "axios";
import { CheckCircleOutlined } from "@ant-design/icons";
import { getEndTime, getStartTime } from "../utils/timeConvert";
import { currencyFormat } from "../utils/currencyFormat";

export default function Invoices() {
  const { stationId } = useParams();
  const [returnStation, setReturnStation] = useState(null);

  const renting = JSON.parse(localStorage.getItem("renting"));
  const bike = renting?.bike;
  const user = renting?.user;
  const creditInfo = user?.creditCard;

  useEffect(() => {
    if (!stationId) return;
    axios
      .get("http://localhost:8080/api/bike-station/detail/" + stationId)
      .then((response) => setReturnStation(response.data))
      .catch((error) => {
        console.log(error);
      });
  }, [stationId]);

  const [returnMessage, setReturnMessage] = useState(null);
  const [loading, setLoading] = useState(false);
  const [activeBtn, setActiveBtn] = useState(false);

  const handleSubmit = (values) => {
    setLoading(true);
    const returnMessage = JSON.parse(localStorage.getItem("return-message"));
    setReturnMessage(returnMessage);
    // axios.get("http://localhost:8080/api/return-bike/caculateTotalCost/" + renting.id)
    // 	.then(response => setReturnMessage(response.data))
    // 	.catch((error) => console.log(error))
    setTimeout(() => {
      setLoading(false);
      setActiveBtn(true);
    }, 3000);
  };

  const newRent = JSON.parse(localStorage.getItem("new-renting"));

  const handleBackToHome = () => {
    if (!activeBtn) return;
    window.location.href = "http://localhost:3000";
    localStorage.removeItem("renting");
    localStorage.removeItem("new-renting");
    localStorage.removeItem("return-message");
    localStorage.setItem(
      "rental-time",
      JSON.stringify({ hours: 0, minutes: 0, seconds: 0 })
    );
  };

  const [endTime, setEndTime] = useState(Date.now());

  return (
    <div className="max-w-5xl mx-auto">
      <SubHeader title={"Invoices"} />
      <div className="grid grid-cols-2 mt-10">
        <div>
          <h2 className="text-center font-semibold mb-8">Bike Information</h2>
          <div className="grid grid-cols-2 gap-4">
            <h2 className="font-medium">Number Plate</h2>
            <h2 className="font-medium">Group 2</h2>
            <h2 className="font-medium">Barcode</h2>
            <h2 className="font-medium">{renting?.bike?.barcode}</h2>
            <h2 className="font-medium">Start time</h2>
            <h2 className="font-medium">{getStartTime(renting?.startTime)}</h2>
            <h2 className="font-medium">End time</h2>
            <h2 className="font-medium">{getEndTime(endTime)}</h2>
            <h2 className="font-medium">Return to</h2>
            <h2 className="font-medium">{returnStation?.name}</h2>
            <h2 className="font-medium">Deposite</h2>
            <h2 className="font-medium">
              {currencyFormat(bike?.bikeType?.depositPrice)}
            </h2>
            <h2 className="font-medium">Fee</h2>
            <h2 className="font-medium">{currencyFormat(0)}</h2>
            <h2 className="font-medium">Total</h2>
            <h2 className="font-medium">
              {currencyFormat(newRent?.totalCost)}
            </h2>
          </div>
          {/* <div onClick={() => window.history.back()} className='mt-[48px] flex justify-center'><Button>Cancel</Button></div> */}
        </div>
        <div className="px-[100px]">
          <h2 className="text-center font-semibold mb-8">Card Information</h2>

          <Form
            onFinish={handleSubmit}
            autoComplete="off"
            className="flex flex-col gap-4"
          >
            <Form.Item
              name={"cardcode"}
              rules={[
                {
                  required: true,
                  message: "Please enter the cardcode",
                },
              ]}
              className="mb-0"
              label="Card code"
              labelCol={{ span: 24 }} // Đặt label trên một dòng
              wrapperCol={{ span: 24 }} // Đặt input trên một dòng
              initialValue={creditInfo?.cardNumber}
            >
              <Input value={creditInfo?.cardNumber} disabled />
            </Form.Item>
            <Form.Item
              name={"cardowner"}
              rules={[
                {
                  required: true,
                  message: "Please enter the cardowner",
                },
              ]}
              className="mb-0"
              label="Card's Owner"
              labelCol={{ span: 24 }} // Đặt label trên một dòng
              wrapperCol={{ span: 24 }} // Đặt input trên một dòng
              initialValue={user?.email}
            >
              <Input disabled value={user?.email} />
            </Form.Item>
            <Form.Item
              name={"expiration"}
              rules={[
                {
                  required: true,
                  message: "Please enter the expiration",
                },
              ]}
              className="mb-0"
              label="Expiration date"
              labelCol={{ span: 24 }} // Đặt label trên một dòng
              wrapperCol={{ span: 24 }} // Đặt input trên một dòng
              initialValue={"1"}
            >
              <Input disabled />
            </Form.Item>
            <Form.Item
              name={"password"}
              rules={[
                {
                  required: true,
                  message: "Please enter your password",
                  whitespace: true,
                },
              ]}
              label="Security code"
              labelCol={{ span: 24 }} // Đặt label trên một dòng
              wrapperCol={{ span: 24 }} // Đặt input trên một dòng
            >
              <Input.Password />
            </Form.Item>
            <div className="flex justify-center gap-8">
              <Form.Item className="mb-0">
                <Button htmlType="submit">Submit</Button>
              </Form.Item>
            </div>
          </Form>
          <Modal
            visible={returnMessage}
            title="Return Bike"
            cancelButtonProps={{ style: { display: "none" } }}
            okButtonProps={{ type: "primary", ghost: true }}
            okText="Back to home"
            closeIcon={null}
            onOk={handleBackToHome}
          >
            <div className="flex items-center justify-center py-2">
              {loading ? (
                <Spin />
              ) : (
                <span className="flex items-center gap-2">
                  {returnMessage}{" "}
                  <CheckCircleOutlined className="text-green-500 text-[20px]" />
                </span>
              )}
            </div>
          </Modal>
        </div>
      </div>
    </div>
  );
}
