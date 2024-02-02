import React from 'react'
import { AiOutlineHome } from 'react-icons/ai'
import { BsChevronCompactRight } from 'react-icons/bs'
import { Link, useLocation } from 'react-router-dom'

export default function SubHeader({ title }) {
    const location = useLocation()
    const path = location.pathname.match('/return/*') ? location.pathname : '/';

    return (
        <div className='h-16 flex gap-2'>
            <div className='h-full text-5xl flex items-center'>
                <Link to={path}><AiOutlineHome /></Link>
            </div>
            <div className='h-full text-3xl flex items-center'>
                <BsChevronCompactRight />
            </div>
            <div className='flex items-center font-medium'>
                {title}
            </div>

        </div>
    )
}
