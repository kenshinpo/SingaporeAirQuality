package com.pochih.singaporeairquality.http.response;

import java.util.Date;
import java.util.List;

/**
 * Created by PoChih(A-Po) on 2018/02/03.
 */

public class PsiResponse {
    private List<Region> region_metadata;
    private List<Item> items;
    private ApiInfo api_info;

    public List<Region> getRegion_metadata() {
        return region_metadata;
    }

    public void setRegion_metadata(List<Region> region_metadata) {
        this.region_metadata = region_metadata;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ApiInfo getApi_info() {
        return api_info;
    }

    public void setApi_info(ApiInfo api_info) {
        this.api_info = api_info;
    }

    public class Region {
        private String name;
        private Location label_location;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Location getLabel_location() {
            return label_location;
        }

        public void setLabel_location(Location label_location) {
            this.label_location = label_location;
        }

        public class Location {
            private double latitude;
            private double longitude;

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }
    }

    public class Item {
        private Date timestamp;
        private Date update_timestamp;
        private Readings readings;

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public Date getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(Date update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public Readings getReadings() {
            return readings;
        }

        public void setReadings(Readings readings) {
            this.readings = readings;
        }

        public class Readings {
            private Reading o3_sub_index;
            private Reading pm10_twenty_four_hourly;
            private Reading pm10_sub_index;
            private Reading co_sub_index;
            private Reading pm25_twenty_four_hourly;
            private Reading so2_sub_index;
            private Reading co_eight_hour_max;
            private Reading no2_one_hour_max;
            private Reading so2_twenty_four_hourly;
            private Reading pm25_sub_index;
            private Reading psi_twenty_four_hourly;
            private Reading o3_eight_hour_max;
            private Reading psi_three_hourly;

            public Reading getO3_sub_index() {
                return o3_sub_index;
            }

            public void setO3_sub_index(Reading o3_sub_index) {
                this.o3_sub_index = o3_sub_index;
            }

            public Reading getPm10_twenty_four_hourly() {
                return pm10_twenty_four_hourly;
            }

            public void setPm10_twenty_four_hourly(Reading pm10_twenty_four_hourly) {
                this.pm10_twenty_four_hourly = pm10_twenty_four_hourly;
            }

            public Reading getPm10_sub_index() {
                return pm10_sub_index;
            }

            public void setPm10_sub_index(Reading pm10_sub_index) {
                this.pm10_sub_index = pm10_sub_index;
            }

            public Reading getCo_sub_index() {
                return co_sub_index;
            }

            public void setCo_sub_index(Reading co_sub_index) {
                this.co_sub_index = co_sub_index;
            }

            public Reading getPm25_twenty_four_hourly() {
                return pm25_twenty_four_hourly;
            }

            public void setPm25_twenty_four_hourly(Reading pm25_twenty_four_hourly) {
                this.pm25_twenty_four_hourly = pm25_twenty_four_hourly;
            }

            public Reading getSo2_sub_index() {
                return so2_sub_index;
            }

            public void setSo2_sub_index(Reading so2_sub_index) {
                this.so2_sub_index = so2_sub_index;
            }

            public Reading getCo_eight_hour_max() {
                return co_eight_hour_max;
            }

            public void setCo_eight_hour_max(Reading co_eight_hour_max) {
                this.co_eight_hour_max = co_eight_hour_max;
            }

            public Reading getNo2_one_hour_max() {
                return no2_one_hour_max;
            }

            public void setNo2_one_hour_max(Reading no2_one_hour_max) {
                this.no2_one_hour_max = no2_one_hour_max;
            }

            public Reading getSo2_twenty_four_hourly() {
                return so2_twenty_four_hourly;
            }

            public void setSo2_twenty_four_hourly(Reading so2_twenty_four_hourly) {
                this.so2_twenty_four_hourly = so2_twenty_four_hourly;
            }

            public Reading getPm25_sub_index() {
                return pm25_sub_index;
            }

            public void setPm25_sub_index(Reading pm25_sub_index) {
                this.pm25_sub_index = pm25_sub_index;
            }

            public Reading getPsi_twenty_four_hourly() {
                return psi_twenty_four_hourly;
            }

            public void setPsi_twenty_four_hourly(Reading psi_twenty_four_hourly) {
                this.psi_twenty_four_hourly = psi_twenty_four_hourly;
            }

            public Reading getO3_eight_hour_max() {
                return o3_eight_hour_max;
            }

            public void setO3_eight_hour_max(Reading o3_eight_hour_max) {
                this.o3_eight_hour_max = o3_eight_hour_max;
            }

            public Reading getPsi_three_hourly() {
                return psi_three_hourly;
            }

            public void setPsi_three_hourly(Reading psi_three_hourly) {
                this.psi_three_hourly = psi_three_hourly;
            }

            public class Reading {
                private double east;
                private double central;
                private double south;
                private double north;
                private double west;
                private double national;

                public double getEast() {
                    return east;
                }

                public void setEast(double east) {
                    this.east = east;
                }

                public double getCentral() {
                    return central;
                }

                public void setCentral(double central) {
                    this.central = central;
                }

                public double getSouth() {
                    return south;
                }

                public void setSouth(double south) {
                    this.south = south;
                }

                public double getNorth() {
                    return north;
                }

                public void setNorth(double north) {
                    this.north = north;
                }

                public double getWest() {
                    return west;
                }

                public void setWest(double west) {
                    this.west = west;
                }

                public double getNational() {
                    return national;
                }

                public void setNational(double national) {
                    this.national = national;
                }
            }
        }


    }

    public class ApiInfo {
        private String status;
    }
}
