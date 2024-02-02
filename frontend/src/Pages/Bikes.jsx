import React, { useEffect, useState } from 'react'
import SubHeader from '../Components/SubHeader'
import BikeStation from '../Components/BikeStation'
import axios from 'axios'

export default function Bike() {

	const [bikes, setBikes] = useState([]);

	useEffect(() => {
		axios.get('http://localhost:8080/api/bike')
			.then((response) => setBikes(response.data))
			.catch((error) => console.log(error));
	}, [])

	return (
		<div className='max-w-5xl mx-auto'>
			<SubHeader title={"List Bike"} />

			<div className='min-h-[300px] border-2 border-gray-400 rounded-lg mt-4 grid grid-cols-4 p-4'>
				{bikes.map(bike => {
					return (
						<div key={bike.id} className='border-2 border-gray-300 rounded-md'>
							<BikeStation bike={bike} />
						</div>
					)
				})}
			</div>
		</div>
	)
}
