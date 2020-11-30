package com.mapbox.navigation.navigator

import com.mapbox.api.directions.v5.DirectionsCriteria
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.navigator.ActiveGuidanceGeometryEncoding
import com.mapbox.navigator.ActiveGuidanceMode
import com.mapbox.navigator.ActiveGuidanceOptions
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ActiveGuidanceOptionsMapperTest {

    // TODO: Mocking routeOptions.coordinates() to emptyList()
    //  Tests need to be updated / new ones need to be added in https://github.com/mapbox/mapbox-navigation-android/pull/3581

    @Test
    fun checksDrivingModePolylineGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE5,
                emptyList()
            ),
            drivingPolyline
        )
    }

    @Test
    fun checksDrivingModePolyline6Geometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE6
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksDrivingModeGeoJsonGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING
        every { routeOptions.geometries() } returns "geojson"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingGeoJson = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KGEO_JSON,
                emptyList()
            ),
            drivingGeoJson
        )
    }

    @Test
    fun checksDrivingModeUnrecognizedGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING
        every { routeOptions.geometries() } returns "unrecognized"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksNullRouteOptions() {
        val routeOptions: RouteOptions? = null
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksNullDirectionsRoute() {
        val directionsRoute = null

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksUnrecognizedModePolylineGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns "unrecognized"
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE5,
                emptyList()
            ),
            drivingPolyline
        )
    }

    @Test
    fun checksUnrecognizedModePolyline6Geometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns "unrecognized"
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE6
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksUnrecognizedModeGeoJsonGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns "unrecognized"
        every { routeOptions.geometries() } returns "geojson"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingGeoJson = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KGEO_JSON,
                emptyList()
            ),
            drivingGeoJson
        )
    }

    @Test
    fun checksUnrecognizedModeUnrecognizedGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns "unrecognized"
        every { routeOptions.geometries() } returns "unrecognized"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksDrivingTrafficModePolylineGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING_TRAFFIC
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE5,
                emptyList()
            ),
            drivingPolyline
        )
    }

    @Test
    fun checksDrivingTrafficModePolyline6Geometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING_TRAFFIC
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE6
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksDrivingTrafficModeGeoJsonGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING
        every { routeOptions.geometries() } returns "geojson"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingGeoJson = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KGEO_JSON,
                emptyList()
            ),
            drivingGeoJson
        )
    }

    @Test
    fun checksDrivingTrafficModeUnrecognizedGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_DRIVING
        every { routeOptions.geometries() } returns "unrecognized"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val drivingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KDRIVING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            drivingPolyline6
        )
    }

    @Test
    fun checksWalkingModePolylineGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_WALKING
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val walkingPolyline = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KWALKING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE5,
                emptyList()
            ),
            walkingPolyline
        )
    }

    @Test
    fun checksWalkingModePolyline6Geometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_WALKING
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE6
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val walkingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KWALKING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            walkingPolyline6
        )
    }

    @Test
    fun checksWalkingModeGeoJsonGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_WALKING
        every { routeOptions.geometries() } returns "geojson"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val walkingGeoJson = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KWALKING,
                ActiveGuidanceGeometryEncoding.KGEO_JSON,
                emptyList()
            ),
            walkingGeoJson
        )
    }

    @Test
    fun checksWalkingModeUnrecognizedGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_WALKING
        every { routeOptions.geometries() } returns "unrecognized"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val walkingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KWALKING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            walkingPolyline6
        )
    }

    @Test
    fun checksCyclingModePolylineGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_CYCLING
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val cyclingPolyline = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KCYCLING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE5,
                emptyList()
            ),
            cyclingPolyline
        )
    }

    @Test
    fun checksCyclingModePolyline6Geometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_CYCLING
        every { routeOptions.geometries() } returns DirectionsCriteria.GEOMETRY_POLYLINE6
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val cyclingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KCYCLING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            cyclingPolyline6
        )
    }

    @Test
    fun checksCyclingModeGeoJsonGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_CYCLING
        every { routeOptions.geometries() } returns "geojson"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val cyclingGeoJson = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KCYCLING,
                ActiveGuidanceGeometryEncoding.KGEO_JSON,
                emptyList()
            ),
            cyclingGeoJson
        )
    }

    @Test
    fun checksCyclingModeUnrecognizedGeometry() {
        val routeOptions: RouteOptions = mockk()
        every { routeOptions.profile() } returns DirectionsCriteria.PROFILE_CYCLING
        every { routeOptions.geometries() } returns "unrecognized"
        every { routeOptions.coordinates() } returns emptyList()
        val directionsRoute: DirectionsRoute = mockk()
        every { directionsRoute.routeOptions() } returns routeOptions

        val cyclingPolyline6 = ActiveGuidanceOptionsMapper.mapFrom(directionsRoute)

        assertEquals(
            ActiveGuidanceOptions(
                ActiveGuidanceMode.KCYCLING,
                ActiveGuidanceGeometryEncoding.KPOLYLINE6,
                emptyList()
            ),
            cyclingPolyline6
        )
    }
}
