import React, { useState, useEffect } from 'react'
import SubHeader from '../Components/SubHeader'
import { Button } from 'antd'
import { useNavigate } from 'react-router-dom';
import { getStartTime } from '../utils/timeConvert';


export default function ViewBike() {
	const navigate = useNavigate();
	const renting = JSON.parse(localStorage.getItem('renting'))
	const bike = renting ? renting.bike : null

	const storageTiming = new Date(getStartTime(renting.startTime)).getTime();

	const [timeRemaining, setTimeRemaining] = useState(calculateTimeRemaining());
	function calculateTimeRemaining() {
		const now = new Date().getTime();
		console.log(now);
		const timeLeft = Math.floor((now - parseInt(storageTiming)) / 1000);
		const hours = Math.floor(timeLeft / 3600)
		const minutes = Math.floor((timeLeft % 3600) / 60)
		const seconds = Math.floor(timeLeft % 60)
		return {
			hours,
			minutes,
			seconds
		};
	}

	useEffect(() => {
		const interval = setInterval(() => {
			const timeLeft = timeRemaining.hours * 3600 + timeRemaining.minutes * 60 + timeRemaining.seconds + 1;
			const hours = Math.floor(timeLeft / 3600)
			const minutes = Math.floor((timeLeft % 3600) / 60)
			const seconds = Math.floor(timeLeft % 60)

			setTimeRemaining({ hours, minutes, seconds })
		}, 1000);

		return () => {
			clearInterval(interval);
		};
	}, [timeRemaining]);

	return (
		renting &&
		<div className='max-w-5xl mx-auto'>
			<SubHeader title={"Bike Information"} />
			<div className='mt-10 grid grid-cols-2 gap-10'>
				<img alt='bike' src='/singlebike.jpg' className='w-full object-cover'></img>
				<div>
					<h2 className='text-center font-semibold justify-between'>Bike Information</h2>
					{bike && <div className='grid grid-cols-2 mt-6 px-8 gap-2'>
						<div>Number Plate</div>
						<div className='font-medium'>{bike.licensePlate}</div>
						<div>Barcode</div>
						<div className='font-medium'>{bike.barcode}</div>
						<div>Type</div>
						<div className='font-medium'>{bike.bikeType.typeName}</div>
						<div>Deposit</div>
						<div className='font-medium'>{bike.bikeType.depositPrice}</div>
						<div>Station</div>
						<div className='font-medium'>{bike.bikeStation.name}</div>
						<div>Battery percentage</div>
						<div className='font-medium'>{bike.batteryLevel}</div>
						{/* <div>Remaining time</div> */}
						{/* <div className='font-medium'>{timeRemaining?.hours}:{timeRemaining?.minutes}:{timeRemaining?.seconds}</div> */}
						<div>Rental time</div>
						<div className='font-medium'>{timeRemaining?.hours}:{timeRemaining?.minutes}:{timeRemaining?.seconds}</div>

					</div>}

					<div className='flex mt-6 justify-center'><Button onClick={() => navigate('/returnBike')}>Return Bike</Button></div>
				</div>
			</div>

		</div>

	)
}
