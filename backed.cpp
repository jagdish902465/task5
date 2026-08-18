# views.py

from django.db.models import Q
from rest_framework.views import APIView
from rest_framework.response import Response
from .models import Video
from .serializers import VideoSerializer


class VideoSearchView(APIView):

    def get(self, request):
        query = request.GET.get("q", "")

        videos = Video.objects.filter(
            Q(title__icontains=query) |
            Q(description__icontains=query)
        ).order_by("-created_at")

        serializer = VideoSerializer(videos, many=True)

        return Response({
            "query": query,
            "count": videos.count(),
            "results": serializer.data
        })