import React, { useEffect, useState } from 'react'
import { Button } from 'antd'
import { SiGooglemaps } from 'react-icons/si'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'

export default function BikeReturn({ station }) {
	const navigate = useNavigate()
	const renting = JSON.parse(localStorage.getItem('renting'));

	const handleReturnBike = () => {
		axios.get("http://localhost:8080/api/return-bike/caculateTotalCost/" + renting.id)
			.then(response => localStorage.setItem('return-message', JSON.stringify(response.data)))
			.then(
				axios.get("http://localhost:8080/api/bike-rental/" + renting.id)
					.then((response) => localStorage.setItem("new-renting", JSON.stringify(response.data)))
					.catch((error) => console.log(error))
			)
			.catch((error) => console.log(error))
			setTimeout(() => {
				navigate('/return/' + station.id)
				
			},1000)
	}

	return (
		<div className='shadow-md rounded-lg p-4'>
			<div className='flex items-center gap-4'>
				<SiGooglemaps className='text-red-700' />
				<span className='font-medium text-xl'>{station.name}</span>
			</div>
			<div className='flex flex-col'>
				<span>Adress: {station.address}</span>
				{/* <span>Availabe bikes: 1</span> */}
				<span>Empty docks: {station.emptySlots}</span>
			</div>

			<div className='grid grid-cols-2 gap-2 mt-4'>
				<Button onClick={handleReturnBike}>Return Bike</Button>
			</div>

		</div>
	)
}
