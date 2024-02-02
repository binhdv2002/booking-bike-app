import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { MdOutlineDirectionsBike } from 'react-icons/md'
import { AiOutlineHome, AiOutlineSearch } from 'react-icons/ai'
import { Button, Form, Input } from 'antd'
// import { getDistance } from 'geolib';
import BikeCard from '../Components/BikeCard'
import axios from 'axios'
import useDebounce from '../hooks/useDebounce'
import { getStationsAxios } from '../utils/getStationsAxios'

export default function Home() {
	const handleSearch = (values) => {
		console.log(values.search)
	}
	const [stationData, setStationData] = useState([])

	const storageRenting = JSON.parse(localStorage.getItem('renting'));

	useEffect(() => {
		getStationsAxios(setStationData)
	}, [])
	// const destination = { latitude: 12.34567, longitude: 23.45678 };
	// const userLocation = { latitude: 10.12345, longitude: 20.67890 };

	// const distance = getDistance(userLocation, destination);

	// console.log(`Khoảng cách giữa vị trí hiện tại và địa điểm là ${distance / 1000} km`);

	const [isSearch, setSearch] = useState(false)

	const [inputValue, setInputValue] = useState('')

	const handleChange = (search) => {
		if (search !== '') {
			setSearch(true)
			setInputValue(search)
		} else {
			setSearch(false)
		}
	}

	const debounce = useDebounce(inputValue, 300);

	const [searchResult, setSearchResult] = useState([])

	useEffect(() => {
		if (!debounce) return;
		axios.get('http://localhost:8080/api/bike-station?search=' + debounce)
			.then(response => setSearchResult(response.data))
			.catch(error => console.error(error))

	}, [debounce])


	useEffect(() => {
		if (isSearch) {
			setStationData(searchResult);
		} else {
			getStationsAxios()
		}
	}, [searchResult])



	return (
		<div>
			<div className='h-20 flex items-center shadow-md'>
				<div className='h-16 max-w-5xl w-full mx-auto flex items-center justify-between'>
					<div className='flex gap-10 items-center'>
						<div className='w-[200px]'>
							<Link to='/' className='text-5xl'><MdOutlineDirectionsBike /></Link>
						</div>
						<div>
							<Link to='/' className='text-5xl'><AiOutlineHome /></Link>
						</div>
						<Form onFinish={handleSearch} autoComplete='off' className='flex gap-2 items-center'>
							<Form.Item
								name={"search"}
								// rules={[
								// 	{
								// 		required: false,
								// 		message: "Please enter the place to search",
								// 	}
								// ]}
								className='mb-0'
							>
								<Input

									onChange={(e) => handleChange(e.target.value)}
									name='search'
									placeholder='Search'
								/>
							</Form.Item>
							<Form.Item className='mb-0'>
								<Button
									htmlType="submit"
									block
								>
									<AiOutlineSearch />
								</Button>
							</Form.Item>
						</Form>
					</div>
					<div className='flex gap-2'>
						{!storageRenting && <Link to='/bikes'><Button>Rent Bike</Button></Link>}
						{storageRenting && <Link to='/viewbike'><Button>View Bike</Button></Link>}
						{storageRenting && <Link to='/returnbike'><Button>Return Bike</Button></Link>}
					</div>
				</div>
			</div>
			<div className='max-w-5xl mx-auto mt-20'>
				<div className='grid grid-cols-3 gap-4' >
					{
						stationData[0] &&
						stationData.map((station, index) => (
							<div key={index}>
								<BikeCard station={station} />
							</div>
						))
					}
				</div>
			</div>
		</div>
	)
}
