import React, { useEffect, useState } from 'react'
import SubHeader from '../Components/SubHeader'
import { Button } from 'antd'
import { Link, useParams } from 'react-router-dom'
import axios from 'axios'

export default function Bike() {
    const { id } = useParams()
    const [bikeData, setBikeData] = useState(null)
    useEffect(() => {
        axios.get(`http://localhost:8080/api/bike/detailBike/${id}`)
            .then((response) => {
                setBikeData(response.data)
                console.log(response.data)
            })
            .catch((error) => console.log(error))
    }, [id])

    return (
        <div className='max-w-5xl mx-auto'>
            <SubHeader title={"Bike Information"} />
            <div className='mt-10 grid grid-cols-2 gap-10'>
                <img alt='bike' src='/singlebike.jpg' className='w-full object-cover'></img>
                <div>
                    <h2 className='text-center font-semibold justify-between'>Bike Information</h2>
                    {
                        bikeData &&
                        <>
                            <div className='grid grid-cols-2 mt-6 px-8 gap-2'>
                                <div>Number Plate</div>
                                <div className='font-medium'>{bikeData.licensePlate}</div>
                                <div>Barcode</div>
                                <div className='font-medium'>{bikeData.barcode}</div>
                                <div>Type</div>
                                <div className='font-medium'>{bikeData.bikeType.typeName}</div>
                                <div>Deposit</div>
                                <div className='font-medium'>{bikeData.bikeType.depositPrice}</div>
                                <div>Station</div>
                                <div className='font-medium'>{bikeData.bikeStation.name}</div>
                                <div>Battery percentage</div>
                                <div className='font-medium'>{bikeData.batteryLevel} {" %"}</div>
                            </div>
                        </>
                    }
                </div>
            </div>
            <div className='flex justify-center gap-10 mt-8'>
                <Button onClick={() => window.history.back()}>Cancel</Button>
                <Link to={`/payment/${id}`}><Button>Rent</Button></Link>
            </div>
        </div>
    )
}
