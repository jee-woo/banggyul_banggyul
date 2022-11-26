import GhDetailComment from '../components/GhDetail/GhDetailComment';
import GhInformation from '../components/GhDetail/GhInformation';
import GhReservation from '../components/GhDetail/GhReservation';
import GhLocation from '../components/GhDetail/GhLocation';
import GhAsk from '../components/GhDetail/GhAsk';
import RatedStar from '../components/common/RatedStar';
import RoomsDetail from '../components/GhDetail/RoomsDetail';
import GhDetailFacilities from '../components/GhDetail/GhDetailFacilities';
import { useEffect, useState } from 'react';
import { getGhDetailData } from '../apis/getGhDetailData';
import { ghDetailProps } from '../types/ghDetailData';

const GuestHouseDetail = () => {
  const [ghdata, setGhData] = useState<ghDetailProps>();
  const [startDay, setStartDay] = useState<string>('2022.11.25');
  const [endDay, setEndDay] = useState<string>('2022.11.24');
  const [dayCal, setDayCal] = useState<number>(0);

  useEffect(() => {
    const data = async () => {
      const ghData = await getGhDetailData(`1?start=${startDay}&end=${endDay}`);
      setGhData(ghData);
    };
    data();
    console.log(startDay, endDay);
  }, [dayCal]);

  return (
    <div className="text-xl font-semibold w-[1120px] mb-[400px]">
      {ghdata && (
        <div>
          <GhInformation
            tags={ghdata.guestHouseTag}
            ghName={ghdata.guestHouseName}
            ghInfo={ghdata.guestHouseInfo}
            ghImage={ghdata.guestHouseImage}
            ghNickname={ghdata.memberNickname}
          />
          <RoomsDetail rooms={ghdata.rooms} />
          <GhReservation
            rooms={ghdata.rooms}
            startDay={startDay}
            endDay={endDay}
            setStartDay={setStartDay}
            setEndDay={setEndDay}
            setDayCal={setDayCal}
            dayCal={dayCal}
          />
          <div className="flex gap-[10px] my-[20px] items-center">
            <RatedStar star={ghdata.guestHouseStar} />
            <div className="text-xl ">후기 {ghdata.reviews.length} 개</div>
          </div>
          <GhDetailComment reviewComment={ghdata.reviews} />
          <GhLocation
            ghLocation={ghdata.guestHouseLocation.split(',')}
            address={ghdata.guestHouseAddress}
          />
          <div className="flex-row justify-between  md:flex mt-[20px]">
            <GhDetailFacilities
              GhFacilities={ghdata.guestHouseDetails}
            ></GhDetailFacilities>
            <GhAsk
              ghName={ghdata.guestHouseName}
              GhPhone={ghdata.guestHousePhone}
            />
          </div>
        </div>
      )}
    </div>
  );
};
export default GuestHouseDetail;
