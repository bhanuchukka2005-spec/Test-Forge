package com.testforge.repo;
import com.testforge.domain.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByRollNumberOrderByAttendanceDateDesc(String rollNumber);
}
