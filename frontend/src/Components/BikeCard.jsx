import React, { useState } from 'react'
import { Button, Modal } from 'antd'
import { SiGooglemaps } from 'react-icons/si'
import { Link } from 'react-router-dom'

export default function BikeCard({station}) {
    const [isOpen, setIsOpen] = useState(false)

    return (
        <div className='shadow-md rounded-lg p-4 h-[200px] relative'>
            <div className='flex items-center gap-4'>
                <SiGooglemaps className='text-red-700' />
                <span className='font-medium text-xl'>{station.name}</span>
            </div>
            <div className='flex flex-col'>
                <span>{`Adress: ${station.address}`}</span>
                <span> {`Availabe bikes: ${station.totalBikes}`}</span>
                <span> {`Empty docks: ${station.emptySlots}`}</span>
            </div>

            <div className='flex left-[90px] gap-2 mt-4 absolute bottom-2'>
                <Link to={`/station/${station.id}`}><Button block>View</Button></Link>
                <Button block onClick={() => setIsOpen(true)}>Distance</Button>
            </div>
            <Modal
                open={isOpen}
                onCancel={() => setIsOpen(false)}
                footer={[
                    <Button key="cancel" className='hidden'>
                        Hủy
                    </Button>,
                    <Button key="ok" type="primary" className='bg-[#4096ff]' onClick={() => setIsOpen(false)}>
                        OK
                    </Button>,
                ]}
            >
                <div className='p-4'>
                    <img alt="minimap" src='/hn.webp' className='w-full h-[200px] object-cover rounded-md'></img>
                    <div className='grid grid-cols-5 mt-4'>
                        <div className='col-span-2 font-semibold'>Address:</div>
                        <div className='col-span-3 font-medium'>{station.address}</div>
                        <div className='col-span-2 font-semibold'>Distance:</div>
                        <div className='col-span-3 font-medium'>{station.walking_distance} {" m"}</div>
                        <div className='col-span-2 font-semibold'>Estimated time:</div>
                        <div className='col-span-3 font-medium'>{Math.floor(station.walking_distance / 50)} {" minutes walk"}</div>
                    </div>
                </div>
            </Modal>
        </div>
    )
}
