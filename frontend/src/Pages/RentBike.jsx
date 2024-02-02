import React from 'react'
import SubHeader from '../Components/SubHeader'
import { Button, Radio } from 'antd'
import { Link } from 'react-router-dom'

export default function RentBike() {
    const handleSubmit = () => {
        console.log('a')
        const data = { numberPlate: 'a', barcode: 'b', type: 'c', deposite: 'd', station: 'e', battery: 'f', remainingTime: 'g' }
        localStorage.setItem('information', JSON.stringify(data))
    }
    return (
        <div className='max-w-5xl mx-auto'>
            <SubHeader title={"Rent Bike"} />
            <div className='mt-10 grid grid-cols-2 gap-10'>
                <img alt='bike' src='/singlebike.jpg' className='w-full object-cover'></img>
                <div>
                    <h2 className='text-center font-semibold'>Bike Information</h2>
                    <div className='grid grid-cols-2 mt-6 px-8 gap-2'>
                        <div>Number Plate</div>
                        <div className='font-medium'>Truong Dinh</div>
                        <div>Barcode</div>
                        <div className='font-medium'>Dock's name</div>
                        <div>Type</div>
                        <div className='font-medium'>Dock's name</div>
                        <div>Deposit</div>
                        <div className='font-medium'>Dock's name</div>
                        <div>Station</div>
                        <div className='font-medium'>Dock's name</div>
                        <div>Battery percentage</div>
                        <div className='font-medium'>Dock's name</div>
                        <div>Remaining time</div>
                        <div className='font-medium'>Dock's name</div>
                    </div>
                    <div className='px-8'>
                        <h2 className='text-sm font-semibold mt-6'>Please choose a payment method</h2>
                        <div className='mt-2'><Radio checked={true} disabled><span className='font-medium'>Credit Card</span></Radio></div>
                    </div>
                </div>
            </div>
            <div className='flex justify-center gap-10 mt-8'>
                <Button onClick={() => window.history.back()}>Cancel</Button>
                <Link to='/payment' onClick={handleSubmit}><Button>Submit</Button></Link>
            </div>
        </div>
    )
}
