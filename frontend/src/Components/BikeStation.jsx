import { Button } from 'antd'
import React from 'react'
import { MdOutlineDirectionsBike } from 'react-icons/md'
import { Link } from 'react-router-dom'

export default function BikeStation({ bike }) {
	return (
		<div>
			<div className='h-[150px]'>
				<img alt='bike' src='/singlebike.jpg' className='w-full h-full object-cover'></img>
			</div>
			<div className='flex px-4 mt-4 gap-10'>
				<img alt='' src='/licenseplate.jpg' className='w-12 h-6 object-cover'></img>
				<h2>{bike?.licensePlate}</h2>
			</div>
			<div className='flex px-4 mt-2 gap-10'>
				<img alt='' src='/barcode.jpg' className='w-12 h-6 object-cover'></img>
				<h2>{bike?.barcode}</h2>
			</div>
			<div className='flex px-4 mt-2 gap-10'>
				<div className='w-12 h-6 flex items-center text-xl pl-4'><MdOutlineDirectionsBike /></div>
				<h2>{bike?.bikeType.typeName}</h2>
			</div>
			<div className='flex mt-4 justify-center mb-4'>
				<Link to={`/bikes/${bike?.id}`}><Button>View</Button></Link>
			</div>
		</div>
	)
}
