import React, { useEffect, useState } from 'react'

import BikeStation from '../Components/BikeStation'
import SubHeader from '../Components/SubHeader'
import { useParams } from 'react-router-dom'
import axios from 'axios'

export default function Station() {
	const { id } = useParams()

	const [stationData, setSationData] = useState(null)
	const [bikesData, setBikesData] = useState([])
	useEffect(() => {
		axios.get(`http://localhost:8080/api/bike-station/detail/${id}`)
			.then((response) => {
				setSationData(response.data)
			})
			.catch((error) => console.log(error))
	}, [id])

	useEffect(() => {
		axios.get(`http://localhost:8080/api/bike/getListBikeIntoStation/${id}`)
			.then((response) => {
				setBikesData(response.data)
				console.log(response.data)
			})
			.catch((error) => console.log(error))
	}, [id])

	return (
		<div className='max-w-5xl mx-auto'>
			{
				stationData &&
				<>
					<SubHeader title={"Station Information"} />
					<div className='mt-10 grid grid-cols-3 gap-4'>
						<div className='col-span-2'>
							<h2 className='text-center font-semibold'>Availble Bikes</h2>
							<div className='min-h-[300px] border-2 border-gray-400 rounded-lg mt-4 grid grid-cols-3 p-4 gap-2'>
								{
									bikesData[0] &&
									bikesData.map((bike, index) => (
										<div className='border-2 border-gray-300 rounded-md' key={index}>

											<BikeStation bike={bike} />

										</div>
									))
								}
							</div>
						</div>
						<div className='grid grid-cols-2 gap-2 pl-4 h-[300px] mt-10'>
							<div>Dock's name</div>
							<div className='font-medium'>{stationData.name}</div>
							<div>Address</div>
							<div className='font-medium'>{stationData.address}</div>
							<div>Area</div>
							<div className='font-medium'>{stationData.acreage_park} {" m2"}</div>
							<div>Empty docks</div>
							<div className='font-medium'>{stationData.emptySlots}</div>
							<div>Availble bikes</div>
							<div className='font-medium'>{stationData.totalBikes}</div>
							<div>Walking time</div>
							<div className='font-medium'>{stationData.walking_distance / 50} {" minutes"}</div>

						</div>
					</div>
				</>
			}
		</div>
	)
}
