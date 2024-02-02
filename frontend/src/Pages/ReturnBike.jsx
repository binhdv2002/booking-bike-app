import React, { useEffect, useState } from 'react'

import BikeReturn from '../Components/BikeReturn'
import SubHeader from '../Components/SubHeader'
import { getStationsAxios } from '../utils/getStationsAxios'


export default function ReturnBike() {
	const [stations, setStations] = useState([])

	useEffect(() => {
		getStationsAxios(setStations);
	}, [])

	return (
		< div className='max-w-5xl mx-auto'>
			<SubHeader title={"Return Bike"} />
			<div className='grid grid-cols-3 gap-4 mt-20'>
				{stations.map(station => {
					return (
						<BikeReturn key={station.id} station={station} />
					)
				})}
			</div>
		</div>
	)
}
